package ru.job4j.todo;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * ValidateService.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateService extends BaseValidate<Item> {

    /**
     * The instance of ValidateService.
     */
    private static final Validate VALIDATE = new ValidateService();

    private ValidateService() {
        super("Item");
    }

    public static Validate getInstance() {
        return VALIDATE;
    }

    @Override
    public boolean add(Item item) {
        boolean result = item != null && item.getName() != null && item.getDesc() != null
                && !item.getName().equals("") && !item.getDesc().equals("") && item.getUser() != null;
        if (result) {
            item.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
            item.setDone(false);
            super.add(item);
        }
        return result;
    }

    @Override
    public boolean update(Item item) {
        boolean result = item != null && getElem(item.getId()) != null;
        if (result) {
            super.update(item);
        }
        return result;
    }
}