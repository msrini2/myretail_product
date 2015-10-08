package com.myretail.product.core.util;


import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class StrUtil 
{
	
	public boolean isEmpty(String value)
	{
		return value == null || value.length() == 0;
	}

	
	public boolean isBlank(String value)
	{
		if (value == null) return true;
		return value.matches("^\\s*$");
	}

	
	public String nullifyEmpty(String src)
	{
		if (src == null) return null;
		src = src.trim();
		if (src.length() == 0) return null;
		return src;
	}

	
	public String nullifyZero(Long src)
	{
		if (src == null) return null;
		if (src == 0L) return null;
		return "" + src;
	}

	
	public String unNullify(String src)
	{
		if (src == null) return "";
		return src.trim();
	}

	
	public boolean areEqual(Object val1, Object val2)
	{
		if (val1 == null && val2 == null) return true;
		if (val1 == null) return false;
		return val1.equals(val2);
	}

	
	public byte parseByte(String value)
	{
		if (value == null) return Byte.MIN_VALUE;
		try
		{
			return Byte.parseByte(value);
		}
		catch (NumberFormatException e)
		{
			return Byte.MIN_VALUE;
		}
	}

	
	public short parseShort(String value)
	{
		if (value == null) return Short.MIN_VALUE;
		try
		{
			return Short.parseShort(value);
		}
		catch (NumberFormatException e)
		{
			return Short.MIN_VALUE;
		}
	}

	
	public int parseInt(String value)
	{
		if (value == null) return Integer.MIN_VALUE;
		try
		{
			return Integer.parseInt(value);
		}
		catch (NumberFormatException e)
		{
			return Integer.MIN_VALUE;
		}
	}

	
	public long parseLong(String value)
	{
		if (value == null) return Long.MIN_VALUE;
		try
		{
			return Long.parseLong(value);
		}
		catch (NumberFormatException e)
		{
			return Long.MIN_VALUE;
		}
	}

	
	public double parseDouble(String value)
	{
		if (value == null) return Double.MIN_VALUE;
		try
		{
			return Double.parseDouble(value);
		}
		catch (NumberFormatException e)
		{
			return Double.MIN_VALUE;
		}
	}

	
	public Long toLong(Object value)
	{
		if (value == null)
		{
			if (logger != null) 
				//logger.error("Value is null");
			return null;
		}
		if (value.getClass().equals(Long.class)) return Long.class.cast(value);
		if (value.getClass().equals(Integer.class)) return Integer.class.cast(value).longValue();
		if (value.getClass().equals(Short.class)) return Short.class.cast(value).longValue();
		if (value.getClass().equals(Byte.class)) return Byte.class.cast(value).longValue();
		if (value.getClass().equals(String.class))
		{
			Long v = parseLong(String.class.cast(value));
			if (v != Long.MIN_VALUE) return v;
			// it may be legitimate Long.MIN_VALUE
			if (String.class.cast(value).equals("" + Long.MIN_VALUE)) return v;
			try
			{
				v = Long.valueOf(String.class.cast(value), 16);
				return v;
			}
			catch (Exception e)
			{
				v = null;
			}
		}
		if (logger != null) {
			//logger.error("Cannot convert value to Long: " + value);
		}
			
		return null;
	}

	
	public Integer toInt(Object value)
	{
		if (value == null)
		{
			if (logger != null) {
				//logger.error("Value is null");
			}
			return null;
		}
		if (value.getClass().equals(Long.class)) return Long.class.cast(value).intValue();
		if (value.getClass().equals(Integer.class)) return Integer.class.cast(value);
		if (value.getClass().equals(Short.class)) return Short.class.cast(value).intValue();
		if (value.getClass().equals(Byte.class)) return Byte.class.cast(value).intValue();
		if (value.getClass().equals(String.class))
		{
			Integer v = parseInt(String.class.cast(value));
			if (v != Integer.MIN_VALUE) return v;
			// it may be legitimate Integer.MIN_VALUE
			if (String.class.cast(value).equals("" + Integer.MIN_VALUE)) return v;
			try
			{
				v = Integer.valueOf(String.class.cast(value), 16);
				return v;
			}
			catch (Exception e)
			{
				v = null;
			}
		}
		if (logger != null) {
			//logger.error("Cannot convert value to Integer: " + value);
		}
		return null;
	}

	
	public Short toShort(Object value)
	{
		if (value == null)
		{
			if (logger != null){
				//logger.error("Value is null");
			}
			return null;
		}
		if (value.getClass().equals(Long.class)) return Long.class.cast(value).shortValue();
		if (value.getClass().equals(Integer.class)) return Integer.class.cast(value).shortValue();
		if (value.getClass().equals(Short.class)) return Short.class.cast(value);
		if (value.getClass().equals(Byte.class)) return Byte.class.cast(value).shortValue();
		if (value.getClass().equals(String.class))
		{
			Short v = parseShort(String.class.cast(value));
			if (v != Short.MIN_VALUE) return v;
			// it may be legitimate Short.MIN_VALUE
			if (String.class.cast(value).equals("" + Short.MIN_VALUE)) return v;
			try
			{
				v = Short.valueOf(String.class.cast(value), 16);
				return v;
			}
			catch (Exception e)
			{
				v = null;
			}
		}
		if (logger != null){
			//logger.error("Cannot convert value to Short: " + value);
		}
		return null;
	}

	
	public Byte toByte(Object value)
	{
		if (value == null)
		{
			if (logger != null){
				//logger.error("Value is null");
			}
			return null;
		}
		if (value.getClass().equals(Long.class)) return Long.class.cast(value).byteValue();
		if (value.getClass().equals(Integer.class)) return Integer.class.cast(value).byteValue();
		if (value.getClass().equals(Short.class)) return Short.class.cast(value).byteValue();
		if (value.getClass().equals(Byte.class)) return Byte.class.cast(value);
		if (value.getClass().equals(String.class))
		{
			Byte v = parseByte(String.class.cast(value));
			if (v != Short.MIN_VALUE) return v;
			// it may be legitimate Short.MIN_VALUE
			if (String.class.cast(value).equals("" + Short.MIN_VALUE)) return v;
			try
			{
				v = Byte.valueOf(String.class.cast(value), 16);
				return v;
			}
			catch (Exception e)
			{
				v = null;
			}
		}
		if (logger != null) {
			//logger.error("Cannot convert value to Short: " + value);
		}
		return null;
	}

	
	public <T> T toType(Object value, Class<T> clazz)
	{
		if (value == null)
		{
			if (logger != null) {
				//logger.error("Value is null");
			}
			return null;
		}
		if (clazz == null)
		{
			if (logger != null) {
				//logger.error("Class type is null");
			}
			return null;
		}
		if (clazz.isAssignableFrom(value.getClass())) return clazz.cast(value);
		if (Long.class.equals(clazz)) return clazz.cast(toLong(value));
		if (Integer.class.equals(clazz)) return clazz.cast(toInt(value));
		if (Short.class.equals(clazz)) return clazz.cast(toShort(value));
		if (Byte.class.equals(clazz)) return clazz.cast(toByte(value));
		if (logger != null) {
			//logger.error("Unexpected class type for value conversion: " + clazz.getName());
		}
		return null;
	}

	
	public Collection<String> asStrings(Collection<Long> longs)
	{
		if (longs == null) return null;
		Collection<String> strings = null;
		try
		{
			@SuppressWarnings("unchecked")
			Collection<String> stringsTmp = longs.getClass().newInstance();
			strings = stringsTmp;
		}
		catch (Exception e)
		{
			strings = new ArrayList<String>(); // create less restrictive collection
		}
		if (longs.isEmpty()) return strings;
		for (Long value : longs)
		{
			strings.add("" + value);
		}
		return strings;
	}

	
	public Collection<Long> asLongs(Collection<String> strings)
	{
		if (strings == null) return null;
		Collection<Long> longs = null;
		try
		{
			@SuppressWarnings("unchecked")
			Collection<Long> longsTmp = strings.getClass().newInstance();
			longs = longsTmp;
		}
		catch (Exception e)
		{
			longs = new ArrayList<Long>(); // create less restrictive collection
		}
		if (strings.isEmpty()) return longs;
		for (String value : strings)
		{
			longs.add(parseLong(value));
		}
		return longs;
	}


	
	
	private Logger logger = LoggerFactory.getLogger(StrUtil.class); // optional, should be set by application, otherwise no logging possible

	public Logger getLogger()
	{
		return logger;
	}

	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}

	public String formatException(Exception e)
	{
		return formatException(e, 25);
	}

	public String formatException(Exception e, int maxStackLines)
	{
		String s = e.getClass().getName() + (e.getMessage() != null ? " : " + e.getMessage() : "");
		if (maxStackLines > 0)
		{
			StringBuffer sb = new StringBuffer(s);
			for (StackTraceElement stackLine : e.getStackTrace())
			{
				sb.append("\n        at ").append(stackLine.toString());
				maxStackLines--;
				if (maxStackLines <= 0) break;
			}
			s = sb.toString();
		}
		return s;
	}
	

}
