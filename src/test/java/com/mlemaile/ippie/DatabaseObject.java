package com.mlemaile.ippie;

import java.time.LocalDate;
import java.time.Month;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.core.Type;

public class DatabaseObject {

    public static final Type  type1;
    public static final Type  type2;
    public static final Type  type3;
    public static final Model model1;
    public static final Model model2;
    public static final Model model3;
    public static final Model model4;
    public static final Model model5;
    public static final Model model6;
    public static final State state1;
    public static final State     state2;
    public static final Component component1;
    public static final Component component2;
    public static final Component component3;
    public static final Component component4;
    public static final Component component5;
    public static final Component component6;
    public static final Component component7;
    public static final Component component8;
    public static final Component component9;
    public static final Component component10;
    static {
        type1 = new Type("RAM");
        type1.setId(1);
        type2 = new Type("PROC");
        type2.setId(2);
        type3 = new Type("CM");
        type3.setId(3);

        model1 = new Model("E5500", type2);
        model1.setId(1);
        model2 = new Model("intel core i7 7700", type2);
        model2.setId(2);
        model3 = new Model("Corsair DDR3 2Go 1333MHz CAS9", type1);
        model3.setId(3);
        model4 = new Model("Kingston DDR3 4Go 1600MHz CAS11", type1);
        model4.setId(4);
        model5 = new Model("Asus PK 5KLM", type3);
        model5.setId(5);
        model6 = new Model("Gigabyte B85", type3);
        model6.setId(6);

        state1 = new State("rangé");
        state1.setId(1);

        state2 = new State("Prêt");
        state2.setId(2);

        component1 = new Component("lacus. Nulla", LocalDate.of(2015, Month.OCTOBER, 4),
                LocalDate.of(2017, Month.FEBRUARY, 21), state1,
                "vehicula. Pellentesque tincidunt tempus", model4,
                "tempor arcu. Vestibulum ut eros non enim commodo hendrerit. Donec porttitor tellus non magna. Nam");
        component1.setId(1);
        component2 = new Component("convallis", LocalDate.of(2014, 11, 24),
                LocalDate.of(2017, 4, 3), state1,
                "neque. Sed eget lacus. Mauris non dui nec urna", model2,
                "gravida non, sollicitudin");
        component2.setId(2);
        component3 = new Component("eu tellus.", LocalDate.of(2014, 02, 19),
                LocalDate.of(2016, 11, 25), state1,
                "purus ac tellus. Suspendisse sed dolor.", model6,
                "leo. Vivamus nibh dolor, nonummy ac, feugiat non, lobortis quis, pede. Suspendisse dui. Fusce diam nunc, ullamcorper");
        component3.setId(3);
        component4 = new Component("nunc ac", LocalDate.of(2014, 07, 05),
                LocalDate.of(2017, 11, 17), state1,
                "dictum eleifend, nunc risus varius orci, in consequat enim diam", model4,
                "molestie pharetra");
        component4.setId(4);
        component5 = new Component("consequat purus.", LocalDate.of(2014, 5, 7),
                LocalDate.of(2016, 4, 13), state1,
                "eros non enim commodo hendrerit. Donec porttitor tellus non magna.",
                model4,
                "vel lectus. Cum sociis natoque penatibus et magnis dis parturient");
        component5.setId(5);
        component6 = new Component("odio. Phasellus", LocalDate.of(2014, 07, 13),
                LocalDate.of(2016, 01, 24), state1,
                "erat vel pede blandit congue. In scelerisque scelerisque dui.", model1,
                "interdum enim non nisi. Aenean eget metus. In nec orci. Donec nibh. Quisque nonummy ipsum non");
        component6.setId(6);
        component7 = new Component("sapien.", LocalDate.of(2014, 8, 24),
                LocalDate.of(2016, 02, 11), state1,
                "dictum ultricies ligula. Nullam enim. Sed nulla ante, iaculis nec,",
                model6, "nisl arcu iaculis");
        component7.setId(7);
        component8 = new Component("ultricies ligula.", LocalDate.of(2015, 06, 05),
                LocalDate.of(2017, 8, 31), state1,
                "nec urna et arcu", model6,
                "in faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus ornare. Fusce mollis. Duis sit");
        component8.setId(8);
        component9 = new Component("faucibus", LocalDate.of(2015, 11, 18),
                LocalDate.of(2016, 6, 9), state1, "Phasellus nulla. Integer", model1,
                "lectus rutrum urna, nec luctus felis purus ac tellus. Suspendisse sed dolor. Fusce mi lorem, vehicula et, rutrum eu, ultrices");
        component9.setId(9);
        component10 = new Component("sed, hendrerit a,", LocalDate.of(2014, 10, 15),
                LocalDate.of(2017, 8, 28), state1, "at, velit. Pellentesque ultricies",
                model4, "lorem ut aliquam iaculis, lacus pede sagittis augue, eu tempor");
        component10.setId(10);
    }

    // we don't want to instantiate this class
    private DatabaseObject() {
    }
}
