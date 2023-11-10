# MP1A: THE LAND OF EREHWON (Feedback)

## Grade: B

**Compiles**: Yes:Yes:Yes:Yes:Yes

**Timeout**: No:No:No:No:Yes

## Test Results
### erehwon.Task1Tests
| Test Status | Count |
| ----------- | ----- |
|tests|5|
|skipped|0|
|failures|2|
|errors|0|
#### Failed Tests
1. `testConstructorSimple() (org.opentest4j.AssertionFailedError: Should get the correct number of vacant cells!
You are working with fractions and not probabilities. ==> expected: <10> but was: <25>)`
1. `testConstructorRounding() (org.opentest4j.AssertionFailedError: expected: <true> but was: <false>)`

### erehwon.Task2Tests
| Test Status | Count |
| ----------- | ----- |
|tests|3|
|skipped|0|
|failures|2|
|errors|0|
#### Failed Tests
1. `testReset1() (org.opentest4j.AssertionFailedError: expected: <10> but was: <27>)`
1. `testReset2() (org.opentest4j.AssertionFailedError: expected: <2000> but was: <3019>)`

### erehwon.Task3Tests
| Test Status | Count |
| ----------- | ----- |
|tests|5|
|skipped|0|
|failures|2|
|errors|0|
#### Failed Tests
1. `testIsHappy3() (org.opentest4j.AssertionFailedError: expected: <false> but was: <true>)`
1. `testFractionHappy() (org.opentest4j.AssertionFailedError: expected: <0.5861111111111111> but was: <0.6225>)`

### erehwon.Task4Tests
| Test Status | Count |
| ----------- | ----- |
|tests|5|
|skipped|0|
|failures|1|
|errors|0|
#### Failed Tests
1. `testOneStepMix3() (org.opentest4j.AssertionFailedError: expected: <0.928> but was: <0.8125>)`

## Code Coverage
### RedBlueGrid
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|1|
|LINE_COVERED|188|
|BRANCH_MISSED|5|
|BRANCH_COVERED|113|

## Comments
### General
- Fantastic work on this MP!
- Great use of helper functions to increase code readability and keep functions small
- Overall great work on the specs! One thing that could make it even better is to standardize the format and vocabulary across authors
- Some of the more complex sections could benefit from comments to describe what they do

### Constructor
- Nitpicks: could use reset in constructor instead of randomizeGrid; could randomizeGrid's implementation be moved to reset?

### setColor
- Can be more specific with return value; when is the color change not successful?

### simulate
- More detail for what exactly this function does would be good


**Detailed Code Analysis**

| Filename | Line | Issue | Explanation |
| -------- | ---- | ----- | ----------- |
|RedBlueGrid.java|7|	GodClass|	Possible God Class (WMC=77, ATFD=52, TCC=24.561%)|
|RedBlueGrid.java|7|	ModifiedCyclomaticComplexity|	The class 'RedBlueGrid' has a Modified Cyclomatic Complexity of 4 (Highest = 10).|
|RedBlueGrid.java|7|	StdCyclomaticComplexity|	The class 'RedBlueGrid' has a Standard Cyclomatic Complexity of 4 (Highest = 10).|
|RedBlueGrid.java|7|	TooManyMethods|	This class has too many methods, consider refactoring it.|
|RedBlueGrid.java|9|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|RedBlueGrid.java|10|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|RedBlueGrid.java|11|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|RedBlueGrid.java|13|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|RedBlueGrid.java|14|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|RedBlueGrid.java|54|	CognitiveComplexity|	The method 'randomizeGrid(double, double)' has a cognitive complexity of 16, current threshold is 15|
|RedBlueGrid.java|54|	CyclomaticComplexity|	The method 'randomizeGrid(double, double)' has a cyclomatic complexity of 11.|
|RedBlueGrid.java|66|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'i' (lines '77'-'77').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'x' (lines '77'-'80').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'x' (lines '77'-'88').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'y' (lines '77'-'81').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'y' (lines '77'-'89').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'i' (lines '77'-'97').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'x' (lines '77'-'97').|
|RedBlueGrid.java|77|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'y' (lines '77'-'97').|
|RedBlueGrid.java|77|	ForLoopVariableCount|	Too many control variables in the for statement|
|RedBlueGrid.java|79|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|80|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'x'|
|RedBlueGrid.java|81|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'y'|
|RedBlueGrid.java|81|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|84|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|87|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|88|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'x'|
|RedBlueGrid.java|89|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'y'|
|RedBlueGrid.java|89|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|91|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|129|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'isValidColor' (lines '129'-'133').|
|RedBlueGrid.java|163|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|RedBlueGrid.java|165|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|RedBlueGrid.java|167|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|RedBlueGrid.java|207|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'happyCounter' (lines '207'-'212').|
|RedBlueGrid.java|212|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'happyCounter' (lines '212'-'212').|
|RedBlueGrid.java|251|	ModifiedCyclomaticComplexity|	The method 'simulate' has a Modified Cyclomatic Complexity of 10.|
|RedBlueGrid.java|251|	StdCyclomaticComplexity|	The method 'simulate' has a Standard Cyclomatic Complexity of 10.|
|RedBlueGrid.java|251|	CognitiveComplexity|	The method 'simulate(int)' has a cognitive complexity of 23, current threshold is 15|
|RedBlueGrid.java|251|	CyclomaticComplexity|	The method 'simulate(int)' has a cyclomatic complexity of 12.|
|RedBlueGrid.java|259|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'vacantX' (lines '259'-'259').|
|RedBlueGrid.java|259|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'vacantX' (lines '259'-'303').|
|RedBlueGrid.java|259|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|260|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'vacantY' (lines '260'-'260').|
|RedBlueGrid.java|260|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'vacantY' (lines '260'-'303').|
|RedBlueGrid.java|260|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|263|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|264|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|286|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|288|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|338|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'maxFraction' (lines '338'-'354').|
|RedBlueGrid.java|339|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'maxIndex' (lines '339'-'344').|
|RedBlueGrid.java|343|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'maxFraction' (lines '343'-'354').|
|RedBlueGrid.java|344|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'maxIndex' (lines '344'-'344').|
|RedBlueGrid.java|348|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|348|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|379|	CyclomaticComplexity|	The method 'getFractionOfSameColor(int, int, Color)' has a cyclomatic complexity of 10.|
|RedBlueGrid.java|386|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'topBound' (lines '386'-'424').|
|RedBlueGrid.java|387|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'bottomBound' (lines '387'-'424').|
|RedBlueGrid.java|402|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|418|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|437|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'cellCount' (lines '437'-'444').|
|RedBlueGrid.java|437|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'cellCount' (lines '437'-'454').|
|RedBlueGrid.java|438|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'sameCount' (lines '438'-'446').|
|RedBlueGrid.java|438|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'sameCount' (lines '438'-'456').|
|RedBlueGrid.java|444|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'cellCount' (lines '444'-'444').|
|RedBlueGrid.java|444|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'cellCount' (lines '444'-'454').|
|RedBlueGrid.java|445|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|446|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'sameCount' (lines '446'-'446').|
|RedBlueGrid.java|446|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'sameCount' (lines '446'-'456').|
|RedBlueGrid.java|454|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'cellCount' (lines '454'-'454').|
|RedBlueGrid.java|455|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|RedBlueGrid.java|456|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'sameCount' (lines '456'-'456').|
## Checkstyle Results
### `RedBlueGrid.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 1 | None | `File does not end with a newline.` |
| 184 | 30 | `'happinessThreshold' hides a field.` |
### `RedBlueGridUI.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 89 | 46 | `'3' is a magic number.` |
| 90 | 29 | `'300' is a magic number.` |
| 90 | 34 | `'150' is a magic number.` |
| 92 | 46 | `'100' is a magic number.` |
| 92 | 51 | `'10' is a magic number.` |
| 100 | 46 | `'100' is a magic number.` |
| 107 | 50 | `'100' is a magic number.` |
| 109 | 51 | `'(' is followed by whitespace.` |
| 116 | 24 | `'100' is a magic number.` |
| 150 | 46 | `'100' is a magic number.` |
| 150 | 73 | `'100' is a magic number.` |
| 150 | 107 | `'100' is a magic number.` |
| 155 | 41 | `'0.8' is a magic number.` |
| 159 | 30 | `'25' is a magic number.` |
| 160 | 13 | `'}' at column 13 should be on the same line as the next part of a multi-block statement (one that directly contains multiple blocks: if/else-if/else, do/while or try/catch/finally).` |
| 161 | 13 | `Catching 'Exception' is not allowed.` |

