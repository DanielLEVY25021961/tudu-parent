/**
 * class package-info :<br/>
 * <ul>
 * <li>Ce package contient les classes du DOMAINE (classes METIER)</li>
 * <li>Il s'agit d'un programme de <b>gestion de 
 * listes de tâches (TODOLIST)</b>
 * auxquelles peuvent collaborer plusieurs USERS.</li>
 * <li>Par exemple, RaphaelBatta collabore à la TODOLIST 
 * relative au projet "Mars 2022" et "Saturne 2030".<br/>
 * PapyGonzales 
 * participe lui à la TODOLIST "Mars 2022" et à la TODOLIST
 *  "Pluton 3000".</li>
 * <li>Chaque tâche "Todo" dans une TODOLIST est assignée 
 * à un seul USER.</li> 
 * <br/>
 * <li>
 * <img src="../../../../../../../javadoc/images/modele_de_domaine.png" 
 * alt="Modèle de DOMAINE" border="1" align="center" />
 * </li>
 * <br/>
 * <li>Un USER <b>collabore</b> à <i>plusieurs</i> 
 * listes de tâches "TodoList" (Cardinalité).</li>
 * <li>Un USER <b>connait</b> les TodoLists auxquelles il collabore 
 * (Navigabilité).</li>
 * <br/>
 * <li>Une TODOLIST <b>est réalisée</b> par <i>plusieurs</i> Users 
 * (Cardinalité).</li>
 * <li>Une TODOLIST <b>connait</b> <i>les</i> Users impliqués 
 * dans sa réalisation (Navigabilité).</li>
 * <li>Une TODOLIST <b>contient</b> <i>plusieurs</i> Tâches 
 * ("Todo" - Cardinalité).</li>
 * <li>Une TODOLIST <b>connaît</b> ses TODOS (Navigabilité).</li>
 * <br/>
 * <li>Une <b>tâche "Todo"</b> n'appartient qu'
 * <i>à une seule</i> liste (Cardinalité).</li>
 * <li>Une <b>tâche "Todo"</b> <b>connait</b> la liste "TodoList" 
 * à laquelle il appartient (Navigabilité).</li>
 * <li>Une tâche "Todo" peut <b>contenir</b> <i>plusieurs</i> sous-tâches 
 * (Cardinalité).</li>
 * <li>Une tâche "Todo" <b>connait</b> ses sous-tâches (Navigabilté).</li>
 * <li>Une tâche "Todo" <b>est assignée</b> à <i>un</i> USER au plus 
 * (Cardinalité).</li>
 * <li>Une tâche "Todo" <b>connait</b> le USER qui lui est 
 * assigné (Navigabilité).</li>
 * <li>le USER <b>ne connait pas directement</b> les "Todo" 
 * qui lui sont assignés. Il connait ses TODOLISTS auxquelles 
 * il collabore.</li>
 * <br/>
 * <li>Un USER assure <b>un ou plusieurs</b> "Role" 
 * (Administrateur, Consultant, ... - Cardinalité)</li>
 * <li>un USER <b>connait</b> les Roles qu'il assure (Navigabilité).</li>
 * <li>Les ROLES ne connaissent pas les USERS qui les assument 
 * (Navigabilité).</li>
 * <br/>
 * <li>Les ROLES ne peuvent être choisis que parmi une liste définie 
 * par une ENUMERATION "RolesEnum".</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 18 nov. 2017
 *
 */
package tudu.domain.model;
