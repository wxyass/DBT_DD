package dd.tsingtaopad.main.visit.shopvisit.term.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端排序</br>
 */
public class TermSequence implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String terminalkey;
    private String sequence;

    /**
     * @return the terminalkey
     */
    public String getTerminalkey()
    {
        return terminalkey;
    }

    /**
     * @param terminalkey the terminalkey to set
     */
    public void setTerminalkey(String terminalkey)
    {
        this.terminalkey = terminalkey;
    }

    /**
     * @return the sequence
     */
    public String getSequence()
    {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(String sequence)
    {
        this.sequence = sequence;
    }

    @Override
    public String toString()
    {
        return "TermSequence [terminalkey=" + terminalkey + ", sequence=" + sequence + "]";
    }
}
