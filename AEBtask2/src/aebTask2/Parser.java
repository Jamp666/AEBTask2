package aebTask2;
//Класс обработки

public class Parser{
        private String[] snp; //ФИО
        private String[] target; //Проверяемый столбец

        public String parseNSP(String cell, String cell2) {
                //ФИО в массив, деление по пробелу
                snp = cell.split("\\s+");
                String surname = snp[0];
                String name = snp[1];
                String patronymic = snp[2];

                //Проверяемый столбец в массив, деление по пробелу и точке
                target = cell2.split("\\s+|\\.");
                int check = 0;
                for (String s : target) {
                        //Поиск слов схожих с фамилией
                        if (halfString(surname).equalsIgnoreCase(halfString(s)) || s.contains(surname.substring(0, surname.length() - 2))) {
                                check++; // + при обнаружении схожести
                        }
                }
                if (check == 0) {
                        //При отсутсвии схожести, поиск слова схожего с именем
                        for (int i = 0; i < target.length; i++) {
                                if (target[i].startsWith(name.substring(0, 1))) {
                                        //Замена Фамилии из ФИО
                                        target[i - 1] = surname;
                                }
                        }
                        //Отредактированная строка создается заного
                        String newCell = "";
                        for (String s : target) {
                                newCell += s + " ";
                        }
                        return newCell;
                }
                return cell2;
        }

        //Метод возвращающий пол строки, для сравнения схожести
        private String halfString (String string) {
                if (string.length() % 2 == 0) {
                        return string.substring(0, string.length() / 2);
                } else {
                        return string.substring(0, string.length() / 2 + 1);
                }
        }
}
