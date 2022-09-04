package com.fasterxml.jackson.perf.xml;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLOutputFactory2;

import com.fasterxml.aalto.stax.InputFactoryImpl;
import com.fasterxml.aalto.stax.OutputFactoryImpl;
import tools.jackson.dataformat.xml.XmlFactory;
import tools.jackson.dataformat.xml.XmlMapper;
import tools.jackson.module.afterburner.AfterburnerModule;

public class StaxProvider
{
	private final static XMLInputFactory2 _inputFactory = new InputFactoryImpl();
	private final static XMLOutputFactory2 _outputFactory = new OutputFactoryImpl();

     public static XmlMapper xmlMapper() {
         return xmlMapper(false);
     }

     public static XmlMapper xmlMapper(boolean afterburner) {
         XmlMapper.Builder builder = XmlMapper.builder(XmlFactory.builder()
                 .xmlInputFactory(_inputFactory)
                 .xmlOutputFactory(_outputFactory)
                 .build());
         if (afterburner) {
             builder = builder.addModule(new AfterburnerModule());
         }
         return builder.build();
	}
}
