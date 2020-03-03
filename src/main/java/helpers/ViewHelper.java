package helpers;

import controllers.MainController;
import models.Tuple;

import java.util.regex.Pattern;

public class ViewHelper {

    private Pattern EmailPattern = Pattern.compile(Consts.EMAIL_REGEX_PATTERN);
    private Pattern SalaryPattern = Pattern.compile(Consts.SALARY_PATTERN);

    public void ResetErrors(MainController context) {
        context.salary_validation.setVisible(false);
        context.name_validation.setVisible(false);
        context.email_validation.setVisible(false);
        context.danger_label.setVisible(false);
    }

    public boolean IsNameValid(String value) {
        return !Utils.isNullOrWhitespace(value);
    }

    public boolean IsEmailValid(String value) {
        return !Utils.isNullOrWhitespace(value) && EmailPattern.matcher(value).matches();
    }

    public Tuple<Boolean, Double> TryGetSalary(String value) {
        try {
            if (Utils.isNullOrWhitespace(value) || !SalaryPattern.matcher(value).matches()) {
                return new Tuple<>(false, 0d);
            }

            return new Tuple<>(true, Double.parseDouble(value));
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return new Tuple<>(false, 0d);
        }
    }
}
