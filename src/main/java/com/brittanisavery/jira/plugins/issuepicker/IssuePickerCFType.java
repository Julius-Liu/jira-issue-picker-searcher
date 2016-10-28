package com.brittanisavery.jira.plugins.issuepicker;

import javax.inject.Inject;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
@Scanned
public class IssuePickerCFType extends AbstractSingleFieldType<Issue> {

	private final IssueManager issueManager;
	
	@Inject
	protected IssuePickerCFType(@ComponentImport CustomFieldValuePersister customFieldValuePersister,
			@ComponentImport GenericConfigManager genericConfigManager, @ComponentImport IssueManager issueManager) {
		super(customFieldValuePersister, genericConfigManager);
		this.issueManager = issueManager;
	}

	@Override
	public Issue getSingularObjectFromString(String string) throws FieldValidationException {

		long issueId = Long.parseLong(string);
		return issueManager.getIssueObject(issueId);
	}

	@Override
	public String getStringFromSingularObject(Issue singularObject) {
		return singularObject != null ? singularObject.getKey() : null;
	}

	@Override
	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_DECIMAL;
	}

	@Override
	protected Object getDbValueFromObject(Issue customFieldObject) {
		return customFieldObject.getId();
	}

	@Override
	protected Issue getObjectFromDbValue(Object databaseValue) throws FieldValidationException {
		Long issueId = (Long) databaseValue;
		return issueManager.getIssueObject(issueId);
	}

	
}
