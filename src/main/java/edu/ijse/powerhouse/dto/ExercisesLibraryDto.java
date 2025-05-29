package edu.ijse.powerhouse.dto;

public class ExercisesLibraryDto {

    private String exercise_id;
    private String name;
    private String description;
    private String muscle_group;
    private String equipment_needed;
    private String difficulty_level;
    private String video_url;
    private String instructions;
    private String added_by;

    public ExercisesLibraryDto() {
    }

    public ExercisesLibraryDto(String exercise_id, String name, String description, String muscle_group, String equipment_needed, String difficulty_level, String video_url, String instructions, String added_by) {
        this.exercise_id = exercise_id;
        this.name = name;
        this.description = description;
        this.muscle_group = muscle_group;
        this.equipment_needed = equipment_needed;
        this.difficulty_level = difficulty_level;
        this.video_url = video_url;
        this.instructions = instructions;
        this.added_by = added_by;
    }

    public String getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
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

    public String getMuscle_group() {
        return muscle_group;
    }

    public void setMuscle_group(String muscle_group) {
        this.muscle_group = muscle_group;
    }

    public String getEquipment_needed() {
        return equipment_needed;
    }

    public void setEquipment_needed(String equipment_needed) {
        this.equipment_needed = equipment_needed;
    }

    public String getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(String difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }
}
