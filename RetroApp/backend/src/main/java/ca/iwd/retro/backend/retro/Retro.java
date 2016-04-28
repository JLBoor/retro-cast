package ca.iwd.retro.backend.retro;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Retro {

    @Id
    private String projectName;
    private String room;

    private List<String> stickies = new ArrayList<>();

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getStickies() {
        return stickies;
    }

    public void setStickies(List<String> stickies) {
        this.stickies = stickies;
    }
}
