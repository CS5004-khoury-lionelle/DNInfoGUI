# Domain Name Information Part 2 - GUI Addition - Instructions

For this assignment, you are going to extend what you did in the first part of the [Domain Name Information assignment](https://github.com/CS5004-khoury-lionelle/DNInfo/tree/main/instructions). In this part, you will be adding a GUI to the program. 

"Living with your code" is a common expression used in computer science. It means when you write code you should expect to have to come back to it and work with it again. This assignment is meant to give you a sense of that by having you use the code from your previous assignment, while adding more features to that code base. 


## Learning Objectives
* Understand the process of adding a GUI to an existing program
* Modify existing code to work with multiple types of views / adding new features.


## Instructions

For this assignment, you will be adding two `views` to your DNInfo application. The first view will be a console view, which we already provided in [ConsoleView.java](../src/main/java/student/view/ConsoleView.java). It assumes you are using the interface provided for the controller [IController](../src/main/java/student/controller/IController.java). The second view will be a GUI view, which you will need to implement from scratch. 

Additionally, you need to modify the DNInfo command line argument options to enable both the console view and the GUI view.

* `-c` or `--console` will enable the console view. 
* `-g` or `--gui` will enable the GUI view.

When the console or gui are enabled, the rest of the command line arguments are ignored except for `--data` which can still be used to specify a different data file. 

Suggested Parts:
1. Copy your code from the previous assignment into this repository. For ease, the only file we *modified* from the last template is [DNINfoApp.java](../src/main/java/student/DNInfoApp.java). The modification was moving the updated help string there, so you could use it where you want. The other files are all files we added, or the one provided in the previous template. 
2. Modify your application to work with ConsoleView.java. We suggest that you DO NOT modify ConsoleView.java, but you may if you really want to modify it. However, our tests will run the application using the `-c` argument. It expects exact input/output. For example, we may provide the following input "export", "test.json", "quit". Which would export the full provided hosts file to test.java, and then end the program.  If the flow doesn't match, you may get very unusual errors/results in the autograder. IMPORTANT: just because a change you made breaks the autograder is not a reason to ask for it to be graded by hand! You still must follow the specified flow of the program as demonstrated in ConsoleView.java.
   * Note: you can pass the autograder with just this part done. 
3. Work on designing your GUI. Grading for the GUI will be based on screenshots and the TAs running your program if they don't see obvious tests/clarity in what you are providing. Your GUI **must** have the following features, but you are free to add more. 
   * A text field for the domain name
   * As area to display the results
   * A button to list every item in the data file
   * A way to export the data to another file


> [!TIP]
> We have once again provided a sample working program, that includes both the console and GUI version. This is a very basic GUI, you do not (nor should you) copy the design, but instead use it as a reference for minimum functionality. The weird X and . characters are just because because it is a demo version.
>
> From the sample_working directory. 
>
> To run the GUI version, you can use the following command
> 
> ```
>  bin/DNInfoGUI -g
> ```
> or if on windows
> ```
> bin\DNInfoGUI.bat -g
> ```
>  To run the console version, you can use the following command
> ```
> bin/DNInfoGUI -c
> ```
> or if on windows
> ```
>   bin\DNInfoGUI.bat -c
> ```
> as with before you can run help by doing
> ```
> bin/DNInfoGUI -h
> ```
> or if on windows
> ```
> bin\DNInfoGUI.bat -h
> ```
> It is worth noting the old DNINfo app functionality (command line arguments) are
> all still there. We only added the views in this assignment. 

### :fire: Task 1: Design 

Before you start writing, it is important to think about design. You DO NOT have to be perfect in your design, so we will come back to this step a few times. 

1. First, become a detective and read through the files provided. Take notes on what you are seeing.  This is a common skill in software engineering, and you will need to do this often as you work with other people's code. [Report.md](../Report.md) has specific questions on the code that may help you. 
2. Go to [DesignDocument.md](../DesignDocument.md) and fill out (ONLY) the initial design sections. 

> [!TIP]
> You are free  to use mermaid or any other UML tools you want, just make sure if you are using another UML tool, you properly link the image in the markdown file. See the resources page, for a list of [UML tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools).


### :fire: Task 2: Implement by Test Driven Development

After your initial design, you should seek to follow the TDD process. This means you should write tests first, and then implement the code to pass those tests. Or better stated, you should write *ONE* test first, implement, and repeat until you have written all your tests. 

1. Figure out a number of tests by brainstorming (done in design)
2. Write **one** test
3. Write **just enough** code to make that test pass
4. Refactor/update  as you go along
5. Repeat steps 2-4 until you have all the tests passing/fully built program

Note: you often don't know all the tests as you write. As such, it is alright to continue to expand your list. This is where people get stuck on TDD. They think they have to know **all** the tests before they start. You don't. You just need to know the next test, and then at the end you double check you have covered all code paths and have full coverage. 

> [!CAUTION]
> Make sure to commit as you development. The bare minimum commits would be after every test, but you probably will have additional commits especially at the beginning. 

#### :raising_hand: Implementation Tips
Here are a few tips to help you out.
* We found breaking up the ArgsController to be beneficial with a focus on loading the Arguments. This then allowed us to check for `-c` or `-g` and then run the appropriate view with a controller created based on the model, or if neither were provided, run the arguments as before.
* Make sure the ConsoleView.java works before you start on the GUI.
* The GUI is *always* easier to write in parts and test those parts. For example, we created the jframe, ran the program, and slowly started adding components. After we had the look at feel we wanted, we then added the functionality (the listeners).
  * To design a GUI, you can use a GUI designer, or this is actually a good place for Copilot or other AI Tool. For example, you could have the following prompt. 
  ``` Create class that extends JPanel. Inside that panel, I want a label that says 'hostname', a text field that is 20 characters long, a button that says 'search'. The search button should be the same length of the text field, and sits under it. The label should be above the text field. The panel should be 200x200 with the components centered. Each component should have a margin of 10 pixels.```
  * This helps find the java swing awt components but **often has errors**. We then would spend time modifying the provided code, but at least, it gives you a starting point. DO NOT take it as is! Make changes, understand what it is creating. 
* For a file chooser/dialog, you can use the JFileChooser. This is a common component in Java Swing. Here is an example of our FileChooser.
```java
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setAcceptAllFileFilterUsed(false);
   fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
   fileChooser.setDialogTitle("Export List");
   fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
   fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
   fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));
   fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt"));

   int userSelection = fileChooser.showSaveDialog(this);

   if (userSelection == JFileChooser.APPROVE_OPTION) {
   // do something
   }
```

### IMPORTANT! 
HAVE FUN! You can play with features. Maybe you want to use the latitude/ longitude to display a location on a map. You are not limited to the bare minimum for this GUI. While we will only grade making sure you have all the minimum features, you can add more. If you go above and beyond, make sure to document it in the final design document - so that the TAs (and thus instructor) can see it! We also encourage sharing some of your final screen shots in Teams if you add a neat feature you want to share. 

 
### :fire: Task 3: Finish Design Document

By this point, your design has probably changed (very few people have perfect designs the first iteration). Update your design document with the final design in the "final design" section. We want to see the history of your first design to your final design. That is a good thing. 


### :fire: Task 4: Finish Report.md

Inside of [Report.md](../Report.md) you will need to answer a series of questions about your program, and about the learning objectives for the module in general. Fill it out. 


> [!IMPORTANT]
> A primary purpose of this activity is to get you working through a process in addition to writing code. In software engineering the process you follow is often just as important as the code you write. This is because the process is what allows you to work with others, and to be able to maintain and update code over time. It may seem tedious right now, but it is a skill that will pay off in the long run.




## Submission

When you are completed, you need to submit your code to gradescope. Go back to Canvas, and click through the link that takes you to the Gradescope assignment. When you submit, you will actually need to pull from your github repository in the dialog that appears. It only pulls the most recent submission, and if you make an update to the repository after you submit, you will need to resubmit to get the latest version in gradescope. 


## 📝 Grading Rubric

1. Learning (AG)
   * Code compiles without issue
   * Code passes all tests 
2. Approaching (AG)
   * Passes the style check.  
3. Meets (MG)
   * README.md is filled out (name, github repo, etc) 
      * With out the link to your repo, the TAs won't grade the rest!
   * DesignDocument (INITIAL) sections are filled out 
   * All methods are tested with JUnit tests
   * Method contain proper javadoc comments (not just javadoc notation but proper wording in the comment)
   * Report.md includes screen shots documenting every feature of your GUI.
4. Exceeds (MG)
   * Code is DRY (Don't Repeat Yourself)
      * Including making use of helping/utility classes to reduce duplication.
   * Student uses proper inheritance without duplication 
   * Methods include tests for edge cases in addition to happy path
   * Proper use of sorts and sort strategy
   * Design document (FINAL) sections are filled out 
     * The notation needs to be correct, and the TAs will double check the final design
     matches the final implementation.
   * Report.md Deeper Thinking question filled out
     * Includes at least two references/citations

Legend:
* AG - Auto-graded
* MG - Manually graded

### Submission Reminder 🚨
For manually graded elements, we only guarantee time to submit for a regrade IF you submit by the DUE DATE. Submitting late may mean it isn't possible for the MG to be graded before the AVAILABLE BY DATE, removing any windows for you to resubmit in time. While it will be graded, it is always best to submit by the due date, so you have full opportunity to improve your grade.

If you need a reminder about the grading policy, please review the syllabus and the canvas page on 'formative/summative' grading. This class uses a unique grading system that will allow you to be flexible with due dates and multiple resubmissions (if you submit with time for TAs to give feedback), but we also ask that you continue to work on the assignment until you get a full grade.


### Autograder Limitation
Currently the autograder is limited in how it can test. As such, when it comes across an error it just stops. This means that if you have multiple errors in your code, you may only see the first one. We are working on improving this, but for now, you will need to fix the first error, and then rerun the tests to see the next error. Eventually, if every test passes, you will get the single point. It also may give you points for valid style, while errors exist in the code - so really assume the first 2 points are done together. 
