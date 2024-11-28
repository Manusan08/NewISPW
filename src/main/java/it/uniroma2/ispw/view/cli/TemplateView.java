package it.uniroma2.ispw.view.cli;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class TemplateView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\033[35m";

    protected UserBean usrBean;

    public abstract void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption;

    protected abstract List<String> getOptions();

    protected abstract String getHeader();

    public int userChoice() {
        printHeader(getHeader());
        List<String> options = getOptions();
        return operationMenu("Come vuoi procedere?", options);
    }

    protected TemplateView() {
    }

    protected TemplateView(UserBean usrBean) {
        this.usrBean = usrBean;
    }

    protected void printHeader(String headerText) {
        int width = 50;
        String border = "-".repeat(width);

        System.out.println();
        System.out.printf("%s%s%n", ANSI_CYAN, border);
        System.out.printf("%" + ((width + headerText.length()) / 2) + "s%n", headerText);
        System.out.printf("%s%s%n", border, ANSI_RESET);
    }

    protected int operationMenu(String title, List<String> options) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.printf("%n\033[34m--- %s ---\033[0m%n", title);
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, options.get(i));
        }

        do {
            System.out.println();
            System.out.printf("Scegli un'opzione (1-%d): ", options.size());

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > options.size()) {
                    System.out.printf("%sPer favore inserisci un numero tra 1 e %d%s%n", ANSI_RED, options.size(), ANSI_RESET);
                    choice = 0;
                }
            } else {
                System.out.printf("%sInput non valido. Per favore inserisci un numero.%s%n", ANSI_RED, ANSI_RESET);
                scanner.next();
            }
        } while (choice == 0);

        return choice;
    }

    public String getDesiredIn(String title, String inMsg) throws CampiVuotiExeption, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printHeader(title);
        System.out.print(inMsg);
        String input = reader.readLine();
        if (input == null || input.trim().isEmpty()) {
            throw new CampiVuotiExeption("Il campo non può essere vuoto.");
        }
        return input;
    }

    public <T> void printTable(List<T> list) throws SystemException {
        Method[] methods = list.get(0).getClass().getDeclaredMethods();
        List<Method> getters = filterGetters(methods);

        List<String> headers = new ArrayList<>();
        List<Integer> columnWidths = new ArrayList<>();

        // Header and column width calculation
        for (Method getter : getters) {
            String header = getHeaderName(getter);
            headers.add(header);
            int maxWidth = header.length();

            for (T item : list) {
                try {
                    String valueString = String.valueOf(getter.invoke(item));
                    maxWidth = Math.max(maxWidth, valueString.length());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new SystemException();
                }
            }
            columnWidths.add(maxWidth);
        }

        printTableHeader(headers, columnWidths);

        // Print rows
        for (T item : list) {
            for (int i = 0; i < getters.size(); i++) {
                try {
                    String valueString = String.valueOf(getters.get(i).invoke(item));
                    System.out.printf("%-" + columnWidths.get(i) + "s ", valueString);
                } catch (Exception e) {
                    System.out.printf("%-" + columnWidths.get(i) + "s ", "Errore");
                }
            }
            System.out.println();
        }
    }

    private void printTableHeader(List<String> headers, List<Integer> columnWidths) {
        for (int i = 0; i < headers.size(); i++) {
            // Utilizzare il format specifier senza concatenazione
            System.out.printf("%s%-" + columnWidths.get(i) + "s%s ", ANSI_PURPLE, headers.get(i), ANSI_RESET);
        }
        System.out.println();

        for (int width : columnWidths) {
            // Mantenere la linea divisoria senza concatenazione
            System.out.print("-".repeat(width) + " ");
        }
        System.out.println();
    }

    private static List<Method> filterGetters(Method[] methods) {
        return Arrays.stream(methods)
                .filter(m -> (m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterCount() == 0)
                .toList();
    }

    private static String getHeaderName(Method method) {
        String name = method.getName();
        if (name.startsWith("get")) {
            return name.substring(3);
        } else if (name.startsWith("is")) {
            return name.substring(2);
        }
        return name;
    }

    public static String formatWithFiveDigits(String string) {
        return String.format("%-5s", string);
    }
}
