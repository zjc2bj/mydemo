package org.hibernate.demo.validation;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.demo.validation.bean.Order;
import org.hibernate.demo.validation.bean.Product;
import org.hibernate.validator.metadata.ConstraintHelper;

/**
 * @see {@link ConstraintHelper#ConstraintHelper()}
 */
public class ValidateDemo {
    public static void main(String[] args) {
        Order order = new Order();
        order.setOrderId("1");
        order.setCustomer(null);
        order.setEmail("11@");
        order.setAddress("deee");
        order.setStatus("stop");
        order.setCreateDate(new Date());
        Product product = new Product();
        product.setProductName(null);
        product.setPrice(Float.valueOf("1"));
        order.setProduct(product);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        // StringBuffer buf = new StringBuffer();
        // ResourceBundle bundle = ResourceBundle.getBundle("message");
        for (ConstraintViolation<Order> violation : violations) {
            // buf.append("--" +
            // bundle.getString(violation.getPropertyPath().toString())).append(
            // "    " + violation.getMessage() + "\n");
            System.out.println(violation.getPropertyPath() + " ：" + violation.getMessage());
            System.out.println(violation.getMessageTemplate() + "  -- " + violation.getConstraintDescriptor());
            System.out.println("*******************************");
        }
        // System.out.println("--------------------------------------------------");
        // System.out.println("--------------------------------------------------");
        // System.out.println(buf);
    }
}
