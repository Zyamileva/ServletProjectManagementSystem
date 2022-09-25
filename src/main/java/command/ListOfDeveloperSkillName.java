package command;

import model.dto.DevelopersDto;
import model.dto.SkillsDto;
import service.DeveloperService;
import service.SkillsService;
import view.View;

import java.util.List;

public class ListOfDeveloperSkillName implements Command {
    public static final String LIST_OF_SKILL_DEVELOPERS = "query_3";
    private final View view;
    private final DeveloperService developersService;
    private final List<String> nameSkills;

    public ListOfDeveloperSkillName(View view, DeveloperService developersService, SkillsService skillsService) {
        this.view = view;
        this.developersService = developersService;
        List<SkillsDto> skills = skillsService.findAll();
        this.nameSkills = skills.stream().map(SkillsDto::getName).toList();
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_OF_SKILL_DEVELOPERS));
    }

    @Override
    public void execute() {
        view.write("Enter name of skills: Java, C++, C#, JS");
        while (true) {
            String skill = view.read();
            if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                List<DevelopersDto> developers = developersService.listOfSkillNameDevelopers(skill);
                developers.forEach(System.out::println);
                view.write(String.format("Enter %s to see all command", Help.HELP));
                break;
            }
        }
    }
}
