package Matricula;

public class Matricula
{
    protected int ra;
    protected int codDisciplina;
    protected float nota;
    protected float frequencia;

    public Matricula(int ra, int codDisc, float nota, float freq) throws Exception
    {
        this.setRa(ra);
        this.setCodDisciplina(codDisc);
        this.setNota(nota);
        this.setFrequencia(freq);
    }

    public void setRa(int ra) throws Exception
    {
        if(ra <= 0)
            throw new Exception("RA inválido");

        this.ra = ra;
    }

    public void setCodDisciplina(int cod) throws Exception
    {
        if(cod <= 0)
            throw new Exception("Código de disciplina inválido");

        this.codDisciplina = cod;
    }

    public void setNota(float nota) throws Exception
    {
        if(nota < 0 || nota > 10)
            throw new Exception("nota inválida");

        this.nota = nota;
    }

    public void setFrequencia(float freq) throws Exception
    {
        if(freq < 0.0 || freq > 1.0)
            throw new Exception("Frequência inválida");

        this.frequencia = freq;
    }

    public int getRa()
    {
        return this.ra;
    }

    public int getCodDisciplina()
    {
        return this.codDisciplina;
    }

    public float getNota()
    {
        return this.nota;
    }

    public float getFrequencia()
    {
        return this.frequencia;
    }

    public String toString()
    {
        String ret = "";

        ret += "RA: " + this.ra + "/n";
        ret += "CÓDIGO DA DISCIPLINA: " + this.codDisciplina + "/n";
        ret += "NOTA: " + this.nota + "/n";
        ret += "FREQUÊNCIA: " + this.frequencia + "/n";

        return ret;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;

        if(obj == this)
            return true;

        if(obj.getClass() != this.getClass())
            return false;

        Matricula mat = (Matricula)obj;

        if(mat.getRa() != this.getRa() || mat.getCodDisciplina() != this.getCodDisciplina() || mat.getNota() != this.getNota() || mat.getFrequencia() != this.getFrequencia())
            return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 666;

        ret = ret * 11 + new Integer(this.ra).hashCode();
        ret = ret * 11 + new Integer(this.codDisciplina).hashCode();
        ret = ret * 11 + new Float(this.nota).hashCode();
        ret = ret * 11 + new Float(this.frequencia).hashCode();

        if(ret < 0)
            ret = -ret;

        return ret;
    }

    public Object clone()
    {
        Matricula ret = null;

        try
        {
            ret = new Matricula(this);
        }
        catch(Exception eroo)
        {
            // vazio
        }
        return ret;
    }

    public Matricula(Matricula modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo inválido");

        this.ra = modelo.ra;
        this.codDisciplina = modelo.codDisciplina;
        this.nota = modelo.nota;
        this.frequencia = modelo.frequencia;
    }
}
