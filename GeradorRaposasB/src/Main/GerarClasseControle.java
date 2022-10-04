package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ConverteDatas;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Cohuzer
 */
public class GerarClasseControle {

    public GerarClasseControle(String nomeDaClasse, List<String> atributo, String caminho) {
        Ferramentas f = new Ferramentas();
        List<String> codigo = new ArrayList();
        ConverteDatas converteDatas = new ConverteDatas();

        st st = new st();

        codigo.add("package Controles;\n");

        codigo.add(
                "import java.text.SimpleDateFormat;\n"
                + "import Entidades." + nomeDaClasse + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.Date;\n"
                + "import java.util.Date;\n"
                + "import java.util.List;\n"
                + "import tools.ConverteDatas;\n"
                + "import tools.ManipulaArquivo;");

        codigo.add("\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + "* @author Mateus Batichotti Silva\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + "*/");

        codigo.add("public class Controle" + nomeDaClasse + " {");
        codigo.add("    private List<" + nomeDaClasse + ">lista = new ArrayList<>();");
        codigo.add("    public Controle" + nomeDaClasse + "(){\n}\n");
        codigo.add("ConverteDatas converteDatas = new ConverteDatas();");
        codigo.add("public void limparLista() {\n"
                + "        lista.clear();//zera a lista\n"
                + "    }");
        codigo.add("public void adicionar(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ") {\n"
                + "        lista.add(" + st.plMinus(nomeDaClasse) + ");\n"
                + "    }");
        codigo.add("public List<" + nomeDaClasse + "> listar() {\n"
                + "        return lista;\n"
                + "    }");

        String[] aux = atributo.get(0).split(";");
        codigo.add(" public " + nomeDaClasse + " buscar(" + aux[0] + " " + aux[1] + ") {\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n");
        String s = "";
        switch (aux[0]) {
            case "int":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "byte":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "double":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "float":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "long":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "short":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "String":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "().equals(" + aux[1] + ")) {\n";
                break;
            case "char":
                s = "if (String.valueOf(lista.get(i).get" + st.plMaiusc(aux[1]) + "()).equals(" + aux[1] + ")) {\n";
                break;
            default:
                throw new AssertionError();
        }
        codigo.add(s);

        codigo.add("                return lista.get(i);\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }");
        codigo.add("public void alterar(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ", " + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + "Antigo) {\n"
                + "        lista.set(lista.indexOf(" + st.plMinus(nomeDaClasse) + "Antigo), " + st.plMinus(nomeDaClasse) + ");\n"
                + "\n"
                + "    }");
        codigo.add("public void excluir(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ") {\n"
                + "        lista.remove(" + st.plMinus(nomeDaClasse) + ");\n"
                + "    }");
        codigo.add("public void gravarLista(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        List<String> listaDeString = new ArrayList<>();\n"
                + "        for (" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + " : lista) {\n"
                + "            listaDeString.add(" + st.plMinus(nomeDaClasse) + ".toString());\n"
                + "        }\n"
                + "        manipulaArquivo.salvarArquivo(caminho, listaDeString);\n"
                + "    }");

        s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (aux[0].equals("String") || aux[0].equals("string")) {
                s = s + "aux[" + i + "], ";
            } else if (aux[0].equals("int") || aux[0].equals("Int")) {
                s = s + "Integer.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("double") || aux[0].equals("Double")) {
                s = s + "Double.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("long") || aux[0].equals("Long")) {
                s = s + "Long.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("short") || aux[0].equals("Short")) {
                s = s + "Short.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("byte") || aux[0].equals("Byte")) {
                s = s + "Byte.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("float")|| aux[0].equals("Float")) {
                s = s + "Float.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("char")|| aux[0].equals("Char")) {
                s = s + "aux[" + i + "].charAt(0), ";
            } else if (aux[0].equals("boolean")|| aux[0].equals("Boolean")) {
                s = s + "Boolean.valueOf(aux["+i+"]), ";
            } else if (aux[0].equals("Date") || aux[0].equals("date")) {
                s = s + "converteDatas.converteDeStringParaDate(aux[" + i + "]), ";
            } else if (aux[0].equals("img") || aux[0].equals("Img")) {
                s = s + "aux[" + i + "], ";
            }
        }
        s = s.substring(0, s.length() - 2);
        codigo.add("public void carregarDados(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        if (!manipulaArquivo.existeOArquivo(caminho)) {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }\n"
                + "\n"
                + "        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);\n"
                + "        //converter de CSV para " + nomeDaClasse + "\n"
                + "        " + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ";\n"
                + "        for (String string : listaDeString) {\n"
                + "            String aux[] = string.split(\";\");\n"
                + "            " + st.plMinus(nomeDaClasse) + " = new " + nomeDaClasse + "(" + s + ");\n"
                + "            lista.add(" + st.plMinus(nomeDaClasse) + ");\n"
                + "        }\n"
                + "    }");
        codigo.add("}");//fim da classe

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/Controles/Controle" + nomeDaClasse + ".java", codigo);
    }

}
