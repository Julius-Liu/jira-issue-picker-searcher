package ut.com.brittanisavery.jira.plugins;

import org.junit.Test;
import com.brittanisavery.jira.plugins.api.MyPluginComponent;
import com.brittanisavery.jira.plugins.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}