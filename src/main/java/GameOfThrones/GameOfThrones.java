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
private LinkedTree<FamilyMember> arbolGenealogico = new LinkedTree<>();
    public void loadFile(String pathToFile) throws IOException {
        
        FamilyMember rootTree = new FamilyMember();
        arbolGenealogico.addRoot(rootTree);
        Position<FamilyMember> nodoPadre = null;
        Map<String, FamilyMember> mapConTodosLosPersonajes = new HashMap<>();
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
                    FamilyMember
                            p = new FamilyMember(splited[0], splited[1], splited[2], splited[3], splited[4]);
                    mapConTodosLosPersonajes.put(p.getId(), p);
                }
            }
        } finally {

            it.close();
        }


    }
    public LinkedTree<FamilyMember> getFamily(String surname){
    
        Iterator<Position<FamilyMember>> ite = new BFSIterator<>(arbolGenealogico, arbolGenealogico.root());
        FamilyMember rootDelReturnTree=null;
        boolean encontradoPrimerMiembroFamilia= false;
        Position<FamilyMember> node = null;
        while (ite.hasNext() && !encontradoPrimerMiembroFamilia) {
           node = ite.next();
            if (node.getElement().getApellido().equals(surname)) {
                encontradoPrimerMiembroFamilia=true;
                rootDelReturnTree = node.getElement();
            }
//            arbolGenealogico.children(node);
        }
        arbolGenealogico.children(node);
        //hacer un for sobre los children y meterlos en una pila o algo parcedio
        //y sacarlo y meterlo en el arbol
        LinkedTree<FamilyMember> returnTree= new LinkedTree<FamilyMember>();
        Position <FamilyMember> a= returnTree.addRoot(new FamilyMember());
        returnTree.add(rootDelReturnTree,a);
        System.out.println(returnTree.toString());
        return returnTree;
    
    
    
        
    }

    private Position<FamilyMember> getNodePadre(LinkedTree<FamilyMember> arbolGenealogico, String idPadre) {
        Position<FamilyMember> nodoPadreReturn = null;
        Iterator<Position<FamilyMember>> ite = new BFSIterator<>(arbolGenealogico, arbolGenealogico.root());
        while (ite.hasNext()) {
            Position<FamilyMember> node = ite.next();
            if (node.getElement().getId().equals(idPadre)) {
                nodoPadreReturn = node;

            }
        }
        return nodoPadreReturn;
    }

    private void rellenarArbol(LinkedTree<FamilyMember> arbolGenealogico, Position<FamilyMember> parent, String children, Map<String, FamilyMember> mapConTodosLosPersonajes) {

        FamilyMember hijo = mapConTodosLosPersonajes.get(StringUtils.deleteWhitespace(children));

        arbolGenealogico.add(hijo, parent);


    }

    private String[] NormalizaPersonaje(String line) {
        line = line.replace("=", "");
        line = line.replace("(", "");
        line = line.replace(")", "");
        return line.split("\\s+");
    }
}
