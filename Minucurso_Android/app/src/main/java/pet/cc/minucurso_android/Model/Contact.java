package pet.cc.minucurso_android.Model;

import java.io.Serializable;

public class Contact {

    private String  name;        // Name
    private String  description; // Description
    private String  image;       // Image path
    private boolean isChecked;   //

    public Contact() {
        this("", "", "");
    }

    public Contact(String name) {
        this(name, "Description of " + name, "Default Image", false);
    }

    public Contact(String name, String description) {
        this(name, description, "Default Image", false);
    }

    public Contact(String name, String description, String image) {
        this(name, description, image, false);
    }


    private Contact(String name, String description, String image, boolean isChecked) {
        this.name        = name;
        this.description = description;
        this.image       = image;
        this.isChecked   = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
