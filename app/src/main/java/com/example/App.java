package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jruby.embed.ScriptingContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println(Arrays.asList(new App().namesFromDocumentBuilder()));
        System.out.println(Arrays.asList(new App().namesFromNokogiri()));
    }

    public String[] namesFromDocumentBuilder() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("people.xml"));
        Element root = doc.getDocumentElement();
        NodeList list = root.getElementsByTagName("name");
        if (list == null) {
            return new String[0];
        } else {
            String[] result = new String[list.getLength()];
            for (int i = 0; i < list.getLength(); i++) {
                Element el = (Element) list.item(i);
                result[i] = el.getFirstChild().getNodeValue();
            }
            return result;
        }

    }

    public String[] namesFromNokogiri() throws Exception {
        ScriptingContainer jruby = new ScriptingContainer();
     
        // setting the JRUBY_HOME to the one from the jruby jar - ignoring
        // the environment setting !
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        jruby.getProvider().getRubyInstanceConfig()
                .setJRubyHome(contextClassLoader
                        .getResource("META-INF/jruby.home")
                        .toString()
                        .replaceFirst("^jar:", ""));
        final Map<String, String> env = new HashMap<String, String>();
            env.put("GEM_HOME", ".");
            env.put("GEM_PATH", ".");
            jruby.getProvider().getRubyInstanceConfig().setEnvironment(env);

        Object parser = jruby.runScriptlet(contextClassLoader
                .getResourceAsStream("parser_instance.rb"), "parser_instance.rb");
        
        return jruby.callMethod(parser, 
                    "parse", 
                    contextClassLoader
                        .getResource("people.xml").toString(),
                    String[].class);
    }
}
