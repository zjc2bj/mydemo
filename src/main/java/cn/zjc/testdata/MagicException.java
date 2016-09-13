// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) definits fieldsfirst ansi space safe 

package cn.zjc.testdata;

@SuppressWarnings("serial")
public class MagicException extends RuntimeException {

	protected Throwable cause = null;

	public MagicException() {
	}

	public MagicException(String s) {
		super(s);
	}

	public MagicException(String s, Throwable throwable) {
		super(s);
		cause = throwable;
	}

	public MagicException(Throwable throwable) {
		cause = throwable;
	}

	public Throwable getCause() {
		return cause;
	}

	public String getMessage() {
		if (super.getMessage() == null)
			return cause != null ? ("原因:" + this.cause.getMessage()) : "";
		else
			return super.getMessage();
	}

	public String toString() {
		return getClass().getSimpleName() + ": " + getMessage();
	}
}
