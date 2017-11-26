package GameOfThrones;

import material.tree.LinkedTree;
import material.tree.Position;
import material.tree.iterator.BFSIterator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameOfThrones {

    public void loadFile(String pathToFile) throws IOException {
        LinkedTree<Personaje> arbolGenealogico = new LinkedTree<>();
        Personaje rootTree = new Personaje();
        arbolGenealogico.addRoot(rootTree);
        Position<Personaje> nodoPadre = null;
        Map<String, Personaje> mapConTodosLosPersonajes = new HashMap<>();
        File file = new File(pathToFile);
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                if (line.length() == 2) {

                    arbolGenealogico.add(mapConTodosLosPersonajes.get(line), arbolGenealogico.root());
                }
                if (line.length() == 8) {
                    String idPadre = line.substring(0, 2);
                    String idHijo = line.substring(5);
                    if (nodoPadre == null || !nodoPadre.getElement().getId().equals(idPadre)) {
                        nodoPadre = getNodePadre(arbolGenealogico,idPadre);

                    }

                    rellenarArbol(arbolGenealogico, nodoPadre, idHijo, mapConTodosLosPersonajes);


                }
                if ((line.length() > 9) && (line.length() < 80)) {
                    String[] splited = NormalizaPersonaje(line);
                    Personaje p = new Personaje(splited[0], splited[1], splited[2], splited[3], splited[4]);
                    mapConTodosLosPersonajes.put(p.getId(), p);
                }
            }
        } finally {

            it.close();
        }


    }

    private Position<Personaje> getNodePadre(LinkedTree<Personaje> arbolGenealogico, String idPadre) {
        Position<Personaje> nodoPadreReturn = null;
        Iterator<Position<Personaje>> ite = new BFSIterator<>(arbolGenealogico, arbolGenealogico.root());
        while (ite.hasNext()) {
            Position<Personaje> node = ite.next();
            if (node.getElement().getId().equals(idPadre)) {
                nodoPadreReturn = node;

            }
        }
        return nodoPadreReturn;
    }

    private void rellenarArbol(LinkedTree<Personaje> arbolGenealogico, Position<Personaje> parent, String children, Map<String, Personaje> mapConTodosLosPersonajes) {

        Personaje hijo = mapConTodosLosPersonajes.get(StringUtils.deleteWhitespace(children));

        arbolGenealogico.add(hijo, parent);


    }

    private String[] NormalizaPersonaje(String line) {
        line = line.replace("=", "");
        line = line.replace("(", "");
        line = line.replace(")", "");
        return line.split("\\s+");
    }
}
