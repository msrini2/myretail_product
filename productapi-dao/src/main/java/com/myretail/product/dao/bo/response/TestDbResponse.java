package com.myretail.product.dao.bo.response;

import java.util.Map;

import com.myretail.product.bo.response.BaseResponse;

public class TestDbResponse extends BaseResponse
{

	private String mainProdRead;
	private String mainProdWrite;
	private String replProdRead; // TODO
	private String replPart1Read;
	private String masterPart1Read;
	private String masterPart1Write;
	private Map<String, String> partitions;

	public String getMainProdRead()
	{
		return mainProdRead;
	}

	public void setMainProdRead(String mainProdRead)
	{
		this.mainProdRead = mainProdRead;
	}

	public String getMainProdWrite()
	{
		return mainProdWrite;
	}

	public void setMainProdWrite(String mainProdWrite)
	{
		this.mainProdWrite = mainProdWrite;
	}

	public String getReplProdRead()
	{
		return replProdRead;
	}

	public void setReplProdRead(String replProdRead)
	{
		this.replProdRead = replProdRead;
	}

	public String getReplPart1Read()
	{
		return replPart1Read;
	}

	public void setReplPart1Read(String replPart1Read)
	{
		this.replPart1Read = replPart1Read;
	}

	public String getMasterPart1Read()
	{
		return masterPart1Read;
	}

	public void setMasterPart1Read(String masterPart1Read)
	{
		this.masterPart1Read = masterPart1Read;
	}

	public String getMasterPart1Write()
	{
		return masterPart1Write;
	}

	public void setMasterPart1Write(String masterPart1Write)
	{
		this.masterPart1Write = masterPart1Write;
	}

	public Map<String, String> getPartitions()
	{
		return partitions;
	}

	public void setPartitions(Map<String, String> partitions)
	{
		this.partitions = partitions;
	}

	private static final long serialVersionUID = 551119520831746019L;

}
