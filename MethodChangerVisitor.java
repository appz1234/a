/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obfuscator;

import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cuonglb
 */
public class MethodChangerVisitor {

    private boolean hasMain = false;
    private ArrayList<String> formVars = new ArrayList<String>();
    private ArrayList<String> impls = new ArrayList<String>();
    private ArrayList<String> extendsClass = new ArrayList<String>();

    public ArrayList<String> getFormVars() {
        return formVars;
    }

    public ArrayList<String> getExtendsClass() {
        return extendsClass;
    }

    public ArrayList<String> getImpls() {
        return impls;
    }

    public boolean isHasMain() {
        return hasMain;
    }

    public static Iterable<MatchResult> allMatches(
            final Pattern p, final CharSequence input) {
        return new Iterable<MatchResult>() {

            public Iterator<MatchResult> iterator() {
                return new Iterator<MatchResult>() {
                    // Use a matcher internally.

                    final Matcher matcher = p.matcher(input);
                    // Keep a match around that supports any interleaving of hasNext/next calls.
                    MatchResult pending;

                    public boolean hasNext() {
                        // Lazily fill pending, and avoid calling find() multiple times if the
                        // clients call hasNext() repeatedly before sampling via next().
                        if (pending == null && matcher.find()) {
                            pending = matcher.toMatchResult();
                        }
                        return pending != null;
                    }

                    public MatchResult next() {
                        // Fill pending if necessary (as when clients call next() without
                        // checking hasNext()), throw if not possible.
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        // Consume pending so next call to hasNext() does a find().
                        MatchResult next = pending;
                        pending = null;
                        return next;
                    }

                    /**
                     * Required to satisfy the interface, but unsupported.
                     */
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public void changeMethods(CompilationUnit cu) {
        List<TypeDeclaration> types = cu.getTypes();
        for (TypeDeclaration type : types) {
            List<BodyDeclaration> members = type.getMembers();
            for (BodyDeclaration member : members) {
                if (member instanceof MethodDeclaration) {
                    MethodDeclaration method = (MethodDeclaration) member;
                    changeMethod(method);
                }
            }
        }
    }

    public void changeMethod(MethodDeclaration n) {
        // change the name of the method to upper case
        n.setName(n.getName().toUpperCase());

        // create the new parameter
        Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "value");

        // add the parameter to the method
        ASTHelper.addParameter(n, newArg);
    }

    public void changeMethod(MethodDeclaration n, String newName) {
        // change the name of the method to upper case
        n.setName(newName);

        // create the new parameter
        Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "value");

        // add the parameter to the method
        ASTHelper.addParameter(n, newArg);
    }

    public ArrayList<String> getAllMethod(CompilationUnit cu) {
        ArrayList<String> methods = new ArrayList<String>();
        List<TypeDeclaration> types = cu.getTypes();
        if (types != null && types.size() > 0) {
            for (TypeDeclaration type : types) {
                List<BodyDeclaration> members = type.getMembers();
                for (BodyDeclaration member : members) {
                    if (member instanceof MethodDeclaration) {
                        MethodDeclaration method = (MethodDeclaration) member;
                        String name = method.getName();
                        if (name.equals("main")) {
                            hasMain = true;
                        }
                        if (!name.equals("initComponents") && !name.equals("run") && !name.equals("main")) {
                            methods.add(method.getName());
                        }
                    }
                }
            }
        }
        return methods;
    }

    public ArrayList<String> getClassName(CompilationUnit cu) {
        ArrayList<String> classNames = new ArrayList<String>();
        List<TypeDeclaration> types = cu.getTypes();
        for (TypeDeclaration type : types) {
            ClassOrInterfaceDeclaration c = (ClassOrInterfaceDeclaration) type;
            classNames.add(c.getName());
            List<ClassOrInterfaceType> listImpl = c.getImplements();
            if (listImpl != null && listImpl.size() > 0) {
                for (int i = 0; i < listImpl.size(); i++) {
                    impls.add(listImpl.get(i).getName());
                }
            }
            List<ClassOrInterfaceType> listExtends = c.getExtends();
            if (listExtends != null && listExtends.size() > 0) {
                for (int i = 0; i < listExtends.size(); i++) {
                    extendsClass.add(listExtends.get(i).getName());
                }
            }
        }
        return classNames;
    }

    public ArrayList<String> getAllVariable(CompilationUnit cu) {
        ArrayList<String> vars = new ArrayList<String>();
        List<TypeDeclaration> types = cu.getTypes();

        if (types != null && types.size() > 0) {
            for (TypeDeclaration type : types) {
                List<BodyDeclaration> members = type.getMembers();
                if (members != null && members.size() > 0) {
                    for (BodyDeclaration member : members) {
                        if (member instanceof FieldDeclaration) {
                            FieldDeclaration field = (FieldDeclaration) member;
                            Type typeField = field.getType();
                            if (typeField instanceof ReferenceType) {
                                ReferenceType refField = (ReferenceType) typeField;
                                Type refType = refField.getType();
                                if (refType instanceof ClassOrInterfaceType) {
                                    ClassOrInterfaceType coiField = (ClassOrInterfaceType) refType;
                                    ClassOrInterfaceType scope = coiField.getScope();
                                    String scopeName = null;
                                    if (scope != null) {
                                        scopeName = scope.toString();
                                    }
                                    if (scopeName == null || (!scopeName.equals("javax.swing") && (!scopeName.equals("java.awt")))) {
                                        List<VariableDeclarator> listV = field.getVariables();
                                        for (int i = 0; i < listV.size(); i++) {
                                            vars.add(listV.get(i).getId().getName());
                                        }
                                    } else if (scopeName.equals("javax.swing") || (scopeName.equals("java.awt"))) {
                                        List<VariableDeclarator> listV = field.getVariables();
                                        for (int i = 0; i < listV.size(); i++) {

                                            formVars.add(listV.get(i).getId().getName());
                                        }
                                    }
                                }
                            }
                            if (typeField instanceof PrimitiveType) {
                                List<VariableDeclarator> listVars = field.getVariables();
                                if (listVars != null && listVars.size() > 0) {
                                    for (int i = 0; i < listVars.size(); i++) {
                                        vars.add(listVars.get(i).getId().getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return vars;
    }

    public ArrayList<String> getAllString(String cu) {
        ArrayList allString = new ArrayList();
        Pattern pattern = Pattern.compile("\".*?\"");
        Iterable<MatchResult> allMatches = allMatches(pattern, cu);
        int j = 0;
        String s;
        for (MatchResult match : allMatches) {
            s = match.group();
            allString.add(s);
            System.out.println(allString.get(j) + " at " + match.start());
            j++;
        }
        return allString;
    }
}
