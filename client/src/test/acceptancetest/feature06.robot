| *Setting* | *Value*                   |
| Library   | SwingLibrary              |
| Library   | String                    |
| Library   | Collections               |
| Library   | keywords.MenuItemKeywords |

| *Keyword*                        | *Action*           | *Argument*                         | *Argument*  |          |
| Is Menu Item Selected            | [Arguments]        | ${menuText}                        |             |          |
|                                  | @{menus}=          | Split String                       | ${menuText} | \|       |
|                                  | ${lastItem}=       | Remove From List                   | ${menus}    | -1       |
|                                  | :FOR               | ${menu}                            | IN          | @{menus} |
|                                  |                    | Select From Menu                   | ${menu}     |          |
|                                  | ${selected}=       | Radio Button Menu Item Is Selected | ${lastItem} |          |
|                                  | [return]           | ${selected}                        |             |          |
| Menu Item Should Be Selected     | [Arguments]        | ${menuText}                        |             |          |
|                                  | ${selected}=       | Is Menu Item Selected              | ${menuText} |          |
|                                  | Should Be True     | ${selected}                        |             |          |
| Menu Item Should Not Be Selected | [Arguments]        | ${menuText}                        |             |          |
|                                  | ${selected}=       | Is Menu Item Selected              | ${menuText} |          |
|                                  | Should Not Be True | ${selected}                        |             |          |


| *Test Case*                      | *Action*                         | *Argument*                       | *Argument*       |
| Test Default File Source         | Start Application                | edu.jsu.mcis.gamegogy.Main       |                  |
|                                  | Select Window                    | Gamegogy                         |                  |
|                                  | Menu Item Should Be Selected     | Source|Resource File             |                  |
|                                  | Menu Item Should Not Be Selected | Web Service                      |                  |
|                                  | Close Window                     | Gamegogy                         |                  |
| Test Web Service Source          | Start Application                | edu.jsu.mcis.gamegogy.Main       |                  |
|                                  | Select Window                    | Gamegogy                         |                  |
|                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
|                                  | Should Be Equal                  | 99000                            | ${courseId}      |
|                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
|                                  | Should Be Equal                  | Total                            | ${columnName}    |
|                                  | Select From Combo Box            | courseComboBox                   | 9                |
|                                  | Select From Combo Box            | columnComboBox                   | Assignment 7     |
|                                  | Label Text Should Be             | courseTerm                       | Spring 2012      |
|                                  | Label Text Should Be             | courseEnrollment                 | 25               |
|                                  | Label Text Should Be             | studentId                        | 111122           |
|                                  | Label Text Should Be             | studentName                      | Vance McClain    |
|                                  | Label Text Should Be             | studentEmail                     | vmcclain@jsu.edu |
|                                  | Label Text Should Be             | studentScore                     | 59.0             |
|                                  | Select From Menu                 | Source|Web Service               |                  |
|                                  | Menu Item Should Not Be Selected | Source|Resource File             |                  |
|                                  | Menu Item Should Be Selected     | Web Service                      |                  |
|                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
|                                  | Should Be Equal                  | 99000                            | ${courseId}      |
|                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
|                                  | Should Be Equal                  | Total                            | ${columnName}    |
|                                  | Close Window                     | Gamegogy                         |                  |
| Test Reselected File Source      | Start Application                | edu.jsu.mcis.gamegogy.Main       |                  |
|                                  | Select Window                    | Gamegogy                         |                  |
|                                  | Select From Menu                 | Source|Web Service               |                  |
|                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
|                                  | Should Be Equal                  | 99000                            | ${courseId}      |
|                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
|                                  | Should Be Equal                  | Total                            | ${columnName}    |
|                                  | Select From Combo Box            | courseComboBox                   | 9                |
|                                  | Select From Combo Box            | columnComboBox                   | Assignment 7     |
|                                  | Label Text Should Be             | courseTerm                       | Spring 2012      |
|                                  | Label Text Should Be             | courseEnrollment                 | 25               |
|                                  | Label Text Should Be             | studentId                        | 111122           |
|                                  | Label Text Should Be             | studentName                      | Vance McClain    |
|                                  | Label Text Should Be             | studentEmail                     | vmcclain@jsu.edu |
|                                  | Label Text Should Be             | studentScore                     | 59.0             |
|                                  | Select From Menu                 | Source|Resource File             |                  |
|                                  | Menu Item Should Be Selected     | Source|Resource File             |                  |
|                                  | Menu Item Should Not Be Selected | Web Service                      |                  |
|                                  | ${courseId}=                     | Get Selected Item From Combo Box | courseComboBox   |
|                                  | Should Be Equal                  | 99000                            | ${courseId}      |
|                                  | ${columnName}=                   | Get Selected Item From Combo Box | columnComboBox   |
|                                  | Should Be Equal                  | Total                            | ${columnName}    |
|                                  | Close Window                     | Gamegogy                         |                  |
