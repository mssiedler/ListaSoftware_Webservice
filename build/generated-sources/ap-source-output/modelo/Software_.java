package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Empresa;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2016-08-21T15:39:42")
@StaticMetamodel(Software.class)
public class Software_ { 

    public static volatile SingularAttribute<Software, String> tema;
    public static volatile SingularAttribute<Software, String> categoria;
    public static volatile SingularAttribute<Software, String> nome;
    public static volatile SingularAttribute<Software, Integer> id;
    public static volatile SingularAttribute<Software, Date> verificacao;
    public static volatile SingularAttribute<Software, Empresa> empresa;
    public static volatile SingularAttribute<Software, String> demo;
    public static volatile SingularAttribute<Software, String> descricao;

}