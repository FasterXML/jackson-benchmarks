package com.fasterxml.jackson.perf.xml;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLOutputFactory2;

import com.fasterxml.aalto.stax.InputFactoryImpl;
import com.fasterxml.aalto.stax.OutputFactoryImpl;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class StaxProvider
{
	private final static XMLInputFactory2 _inputFactory = new InputFactoryImpl();
	private final static XMLOutputFactory2 _outputFactory = new OutputFactoryImpl();

	public static XmlMapper xmlMapper() {
		return new XmlMapper(new XmlFactory(_inputFactory, _outputFactory));
	}
}
