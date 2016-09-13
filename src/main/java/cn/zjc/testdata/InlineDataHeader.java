// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) definits fieldsfirst ansi space safe 

package cn.zjc.testdata;


public class InlineDataHeader
{

    private String script = null;
    private int tokenCount = 0;

    public InlineDataHeader()
    {
        tokenCount = 0;
    }

    public String getScript()
    {
        return script;
    }

    public void setScript(String s)
    {
        this.script = s;
    }

    public int getTokenCount()
    {
        return tokenCount;
    }

    public void setTokenCount(int i)
    {
        tokenCount = i;
    }
}
