package io.protostuff.compiler.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.protostuff.compiler.ParserModule;
import io.protostuff.compiler.model.Extension;
import io.protostuff.compiler.model.Proto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author Kostiantyn Shchepanovskyi
 */
public class ExtensionsIT {
    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new ParserModule());
    }

    @Test
    public void test() throws Exception {
        Importer importer = injector.getInstance(Importer.class);
        ProtoContext context = importer.importFile("test/extensions/test.proto");
        Map<String, Extension> a = context.getExtensionMap(".test.extensions.A");
        Assert.assertTrue(a.containsKey("ay"));
        Assert.assertTrue(a.containsKey("az"));
        Map<String, Extension> b = context.getExtensionMap(".test.extensions.A.B");
        Assert.assertTrue(b.containsKey("by"));
        Assert.assertTrue(b.containsKey("bz"));
    }

}
