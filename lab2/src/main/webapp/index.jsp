<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="beans.Entry" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="img/Russia.ico">
    <title>Web project #1</title>
    <link href="style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table id="main-grid">
    <tr>
        <!-- Header -->
        <td id="header-plate" colspan="2">
            <span class="left-aligned">Ageev Dmitriy Sergeevich (P3231)</span>
            <span class="right-aligned">#31001</span>
        </td>
    </tr>

    <tr>
        <!-- Graph -->
        <td class="content-plate" id="graph-plate">
            <div class="plate-top">
                <h2 class="plate-top-title">Graph</h2>
            </div>

            <div class="image-container">
                <svg id="svg" class="svg" height="400" width="400" xmlns="http://www.w3.org/2000/svg">
                    <!--- Coordinate axes --->
                    <line stroke="black" x1="0" x2="400" y1="200" y2="200"></line>
                    <line stroke="black" x1="200" x2="200" y1="0" y2="400"></line>
                    <!--- Axis directions --->
                    <polygon fill="black" stroke="black" points="200,0 195,10 205,10"></polygon>
                    <polygon fill="black" stroke="black" points="400,200 390,195 390,205"></polygon>
                    <!--- Segments --->
                    <line stroke="black" x1="50" x2="50" y1="195" y2="205"></line>
                    <line stroke="black" x1="125" x2="125" y1="195" y2="205"></line>
                    <line stroke="black" x1="275" x2="275" y1="195" y2="205"></line>
                    <line stroke="black" x1="350" x2="350" y1="195" y2="205"></line>
                    <line stroke="black" x1="195" x2="205" y1="50" y2="50"></line>
                    <line stroke="black" x1="195" x2="205" y1="125" y2="125"></line>
                    <line stroke="black" x1="195" x2="205" y1="275" y2="275"></line>
                    <line stroke="black" x1="195" x2="205" y1="350" y2="350"></line>
                    <!--- Units --->
                    <text fill="black" x="40" y="190">-R</text>
                    <text fill="black" x="110" y="190">-R/2</text>
                    <text fill="black" x="265" y="190">R/2</text>
                    <text fill="black" x="345" y="190">R</text>
                    <text fill="black" x="210" y="54">R</text>
                    <text fill="black" x="210" y="128">R/2</text>
                    <text fill="black" x="210" y="278">-R/2</text>
                    <text fill="black" x="210" y="353">-R</text>
                    <text fill="black" x="387" y="190">X</text>
                    <text fill="black" x="210" y="15">Y</text>
                    <!--- Areas --->
                    <polygon fill="#008cf0" fill-opacity="0.5" points="50,125 200,125 200,200 50,200"
                             stroke="black"></polygon>
                    <polygon fill="#e3256b" fill-opacity="0.5" points="350,200 200,50 200,200"
                             stroke="black"></polygon>
                    <path d="M50 200 A 150 150, 0, 0, 0, 200 350 L 200 200 Z" fill="#991199" fill-opacity="0.5"
                          stroke="black"></path>
                    <!--- Shot shadow --->
                    <circle id="dot" fill="blue" color="blue" r="0" cx="0" cy="0"></circle>
                    <%
                        Object rawHistory = application.getAttribute("history");
                        LinkedList<Entry> history;
                        if (rawHistory != null) {
                            if (rawHistory instanceof LinkedList
                                    && !((LinkedList<?>) rawHistory).isEmpty()
                                    && ((LinkedList<?>) rawHistory).getFirst() instanceof Entry) {
                                history = (LinkedList<Entry>) rawHistory;
                            } else {
                                System.out.println("Attempt`s history can't be matched.");
                                history = new LinkedList<>();
                            }
                        } else {
                            history = new LinkedList<>();
                        }
                        if (!history.isEmpty()) {
                            for (Entry shot : history) {
                                double cx = 200 + 150 / shot.getR() * shot.getX();
                                double cy = 200 - 150 / shot.getR() * shot.getY();
                                String color;
                                if (shot.getResult()) {
                                    color = "LIME";
                                } else color = "red";
                                out.println("<circle fill=\"" + (color) + "\" color=\"" +
                                        color + "\" r=\"3\"" + "\" cx=\"" + cx + "\" cy=\"" + cy + "\"></circle>");
                            }
                        }
                    %>
                </svg>
            </div>
        </td>
        <!-- Table -->
        <td class="content-plate" id="table-plate" rowspan="2">
            <div class="plate-top">
                <h2 class="plate-top-title">Table</h2>
            </div>

            <div class="scroll-container" style="background-color: rgba(0, 125, 215, 0)">
                <table id="result-table">
                    <tr class="table-header">
                        <th class="coords-col">X</th>
                        <th class="coords-col">Y</th>
                        <th class="coords-col">R</th>
                        <th class="time-col">Current time</th>
                        <th class="time-col">Execution time</th>
                        <th class="hitres-col">Result</th>
                    </tr>
                    <%
                        if (rawHistory instanceof LinkedList) {

                            history = (LinkedList) rawHistory;
                            for (Iterator it = history.descendingIterator(); it.hasNext(); ) {
                                Object attempt = it.next();
                                if (attempt instanceof Entry) {
                                    Entry responseBean = (Entry) attempt;
                                    String x = String.format("%.3f",responseBean.getX());
                                    String y = String.format("%.3f",responseBean.getY());
                                    String exec = String.format("%.7f",Double.parseDouble(responseBean.getExecuteTime()));

                                    out.println("<tr><td>" + x
                                            + "</td><td>" + y
                                            + "</td><td>" + responseBean.getR()
                                            + "</td><td>" + responseBean.getCurrentTime()
                                            + "</td><td>" + exec
                                            + "</td><td>" + responseBean.getResult() + "</td></tr>");
                                }
                            }
                        }
                    %>
                </table>
            </div>
        </td>
    </tr>

    <tr>
        <!-- Values -->
        <td class="content-plate" id="values-plate">
            <div class="plate-top">
                <h2 class="plate-top-title">Values</h2>
            </div>

            <form id="input-form" action="" method="POST">
                <table id="input-grid">
                    <!-- X Value -->
                    <tr>
                        <td class="input-grid-label" style="font-size: 18px">
                            <label>X:</label>
                        </td>

                        <td class="input-grid-value x-radio-group">
                            <input class="x-radio" name="xval" id="x-button1" type="button" onclick="chooseX(this)"
                                   value="-5" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button2" type="button" onclick="chooseX(this)"
                                   value="-4" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button3" type="button" onclick="chooseX(this)"
                                   value="-3" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button4" type="button" onclick="chooseX(this)"
                                   value="-2" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button5" type="button" onclick="chooseX(this)"
                                   value="-1" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button6" type="button" onclick="chooseX(this)"
                                   value="0" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button7" type="button" onclick="chooseX(this)"
                                   value="1" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button8" type="button" onclick="chooseX(this)"
                                   value="2" style="font-size: 19px">
                            <input class="x-radio" name="xval" id="x-button9" type="button" onclick="chooseX(this)"
                                   value="3" style="font-size: 19px">
                        </td>
                    </tr>

                    <!-- Y Value -->
                    <tr>
                        <td class="input-grid-label">
                            <label for="y-textinput" style="font-size: 18px">Y:</label>
                        </td>

                        <td class="input-grid-value">
                            <input id="y-textinput" type="text" name="yval" maxlength="10" autocomplete="off"
                                   placeholder="Number from -3 to 3...">
                        </td>
                    </tr>

                    <!-- R Value -->
                    <tr>
                        <td class="R-value">
                            <label style="font-size: 18px">R:</label>
                        </td>
                        <td>
                            <div class="center-labeled">
                                <label class="rbox-label" for="r-radio3">
                                    <input class="r-radio" id="r-radio3" type="radio" name="rval" value="1" checked style="font-size: 19px">
                                    1
                                </label>
                            </div>

                            <div class="center-labeled">
                                <label class="add-labeled rbox-label" for="r-radio4">
                                    <input class="r-radio" id="r-radio4" type="radio" name="rval" value="1.5" style="font-size: 19px">
                                    1.5</label>
                            </div>

                            <div class="center-labeled">
                                <label class="add-labeled rbox-label" for="r-radio5">
                                    <input class="r-radio" id="r-radio5" type="radio" name="rval" value="2" style="font-size: 19px">
                                    2
                                </label>
                            </div>

                            <div class="center-labeled">
                                <label class="add-labeled rbox-label" for="r-radio6">
                                    <input class="r-radio" id="r-radio6" type="radio" name="rval" value="2.5" style="font-size: 19px">
                                    2.5
                                </label>
                            </div>

                            <div class="center-labeled">
                                <label class="add-labeled rbox-label" for="r-radio7">
                                    <input class="r-radio" id="r-radio7" type="radio" name="rval" value="3" style="font-size: 19px">
                                    3
                                </label>
                            </div>
                        </td>
                    </tr>

                    <!-- Buttons -->
                    <tr>
                        <td colspan="2">
                            <div class="buttons">
                                <input class="button" type="submit" value="Submit" style="border-radius: 20px">
                                <input id="RESET" class="button" type="reset" value="Reset" onclick="reset()" style="border-radius: 20px">
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>

</table>
</body>
<script src="js/jquery-3.6.0.js"></script>
<script>
    $(document).on('click', 'svg', function (event) {
        mouse_shot(event);
    });
</script>
<script src="js/main.js?<?=filetime('js/main.js')?>"></script>
</html>