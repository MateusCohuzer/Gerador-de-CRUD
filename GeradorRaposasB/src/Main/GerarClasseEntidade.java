package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Cohuzer
 */
public class GerarClasseEntidade {

    public GerarClasseEntidade(String entidade, List<String> atributo, String caminho) {
        Ferramentas f = new Ferramentas();
        List<String> codigo = new ArrayList<>();

        codigo.add("package Entidades;\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + "* @author Mateus Batichotti Silva\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + "*/");
        
        codigo.add("\nimport java.util.Date;\n");
        
        codigo.add("public class " + entidade + " {");

        for (String a : atributo) {
            String aux[] = a.split(";");
            if ("img".equals(aux[0])) {
                aux[0] = "String";
            }
            codigo.add("private " + aux[0] + " " + aux[1] + ";");
        }

        codigo.add("public " + entidade + "() {\n"
                + "    }");

        //gera o construtor
        String s = "";
        for (String a : atributo) {
            String aux[] = a.split(";");
            if ("img".equals(aux[0])) {
                aux[0] = "String";
            }
            s = s + aux[0] + " " + aux[1] + ",";
        }
        s = s.substring(0, s.length() - 1);

        codigo.add("public " + entidade + "(" + s + ") {");

        for (String a : atributo) {
            String aux[] = a.split(";");
            codigo.add("this." + aux[1] + " = " + aux[1] + ";");
        }
        codigo.add("}");

        //métodos GET
        //public double getPeso() {
        for (String a : atributo) {
            String aux[] = a.split(";");
            if ("img".equals(aux[0])) {
                aux[0] = "String";
            }
            codigo.add("public " + aux[0] + " get" + f.plma(aux[1]) + "(){");
            codigo.add("return " + aux[1] + ";");
            codigo.add("}\n");
        }

        //métodos set
        for (String a : atributo) {
            String aux[] = a.split(";");
            if ("img".equals(aux[0])) {
                aux[0] = "String";
            }
            codigo.add("public void" + " set" + f.plma(aux[1])
                    + "(" + aux[0] + " " + aux[1] + "){");
            codigo.add("this." + aux[1] + "=" + aux[1] + ";\n");
            codigo.add("}\n");
        }

        //método toString
        /*
    return id + ";" + nome + ";" + peso;
         */
        codigo.add("@Override\n public String toString(){");
        s = "";
        for (String a : atributo) {
            String aux[] = a.split(";");
            s = s + aux[1] + "+\";\"+" ;
        }
        s = s.substring(0, s.length() - 5);
        
        codigo.add("return "+s+";"+"\n}\n");
        
        codigo.add("}//fim da classe");
        
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho+"/src/Entidades/" + entidade + ".java", codigo);
    }

}
