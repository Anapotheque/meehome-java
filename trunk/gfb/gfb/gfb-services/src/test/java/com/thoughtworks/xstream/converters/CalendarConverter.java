package com.thoughtworks.xstream.converters;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CalendarConverter implements Converter {

        private Locale locale = Locale.getDefault();
        private DateFormat format = null;

        public CalendarConverter(Locale locale) {
                super();
                this.locale = locale;
        }
        public CalendarConverter(DateFormat format) {
            super();
            this.format = format;
    }

        public boolean canConvert(Class clazz) {
                return Calendar.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
                final Calendar calendar = (Calendar) value;
                final Date date = calendar.getTime();
                if(format == null) {
                    format = DateFormat.getDateInstance(DateFormat.FULL, this.locale);
                }
                writer.setValue(format.format(date));
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                final GregorianCalendar calendar = new GregorianCalendar();
                if (format == null) {
                    format = DateFormat.getDateInstance(DateFormat.FULL, this.locale);
                }
                try {
                        calendar.setTime(format.parse(reader.getValue()));
                } catch (ParseException e) {
                        throw new ConversionException(e.getMessage(), e);
                }
                return calendar;
        }

}