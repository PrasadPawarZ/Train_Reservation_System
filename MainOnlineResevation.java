package OnlineReservationSystem;
//Pr
import java.io.File;

public class MainOnlineResevation extends VisualForm {

    public static void main(String[] args) {
        VisualForm form = new VisualForm();
        form.createLoginPage();
        VisualForm.addLoginChangeListener(evt -> {
            if ("userLoggedIn".equals(evt.getPropertyName()) && (boolean) evt.getNewValue()) {
                System.out.println("User logged in successfully.");
                if (new File("D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/UsersData/User" + form.currentUser + ".ser").exists()) {
                    System.out.println(form.currentUser);
                    if (form != null) {
                        try {
                            Objecct loadedForm = VisualForm.loadVisualFormFromFile("D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/UsersData/User" + form.currentUser + ".ser");
                            if (loadedForm != null) {
                                System.out.println("here2");
                                form.copyDataFrom(loadedForm);
                            } else {
                                System.out.println("Failed to load user data.");
                            }
                
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }
}
