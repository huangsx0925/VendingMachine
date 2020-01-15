$(function() {
    hideall();
    productgetdata();
});

let hideall = function() {
    $("#productresult").hide();
    $("#accountresult").hide();
    $("#login").hide();
    $("#loginsuccess").hide();
    $("#loginfail").hide();
    $("#logoutsuccess").hide();
    $("#logoutfail").hide();
    $("#salelist").hide();
    $("#salesuccess").hide();
    $("#salefail").hide();
}

let loginexam = function(success, fail) {
    $.ajax({
        async: false,
        url: "/uirequest?request=checklogin",
        type: "POST",
        data: {},
        contentType: "application/json;charset=UTF-8",
        accept: "*/*",
        success: function(txt) {
            let loginsign = txt.split("_typesign_")[1].replace(/\r|\n/, "");
            if (loginsign === "false") {
                fail();
            } else {
                success();
            }
        }
    });
}

let product = function(txt) {
    $("#productresult").show();
    let productlist = txt.split("_storagesplit_");
    let htmlproductlist = $("#productresult");

    let htmlproductlistcontent = "";
    let cnt = 0;
    productlist.forEach(product => {
        cnt++;
        let productinfo = product.split("_productsplit_");

        let htmlproductcontent = '<tr id="productinfo' + cnt + '">';
        productinfo.forEach(info => {
            htmlproductcontent += "<td>" + info + "</td>";
        });
        htmlproductcontent +=
            '<td>' +
            '<button id="productnumberinc' + cnt + '" onclick="productnumberinc(' + cnt + ')">+</button>' +
            '<div id="productnumber' + cnt + '">' +
            '0' +
            '</div>' +
            '<button id="productnumberdec' + cnt + '" onclick="productnumberdec(' + cnt + ')">-</button>' +
            '</td>';
        htmlproductcontent += "</tr>";

        htmlproductlistcontent += htmlproductcontent;
    });
    htmlproductlistcontent += '<tr><td><button onclick="buygetdata()">buy</button></td></tr>';
    htmlproductlist.html(htmlproductlistcontent);
}

let getproductremain = function(n) {
    return parseInt($("#productinfo" + n).html().split("</td><td>")[3].replace("remaining:", ""));
}

let productnumberinc = function(n) {
    loginexam(
        function() {
            let num = parseInt($("#productnumber" + n).html());
            if (num < getproductremain(n)) {
                num++;
            } else {
                num = getproductremain(n);
            }
            $("#productnumber" + n).html(num);
        },
        function() {
            showlogin();
        }
    );
}

let productnumberdec = function(n) {
    loginexam(
        function() {
            let num = parseInt($("#productnumber" + n).html());
            if (num > 0) {
                num--;
            } else {
                num = 0;
            }
            $("#productnumber" + n).html(num);
        },
        function() {
            showlogin();
        }
    );
}

let account = function(txt) {
    // alert(txt);
    $("#accountresult").show();
    let htmlaccount = $("#accountresult");

    if (txt.split("_accountsplit_")[1] === undefined) {
        htmlaccount.html("<li>No user has logged in yet</li>");
        htmllogin = '<div id="enterlogin" onclick="showlogin()">login?</div>';
        htmlaccount.append(htmllogin);
    } else {
        let htmlaccountcontent = "";
        let account = txt.split("_accountsplit_");
        htmlaccountcontent += "<li>" + account[0] + "</li>";
        htmlaccountcontent += "<li>" + account[1] + "</li>";

        htmlaccountcontent += "<table>";
        let salelist = account[2].split("_salelistsplit_");
        salelist.forEach(sale => {
            htmlaccountcontent += "<tr>";
            let saleinfo = sale.split("_salesplit_");
            saleinfo.forEach(info => {
                htmlaccountcontent += "<td>" + info + "</td>";
            });
            htmlaccountcontent += "</tr>";
        });
        htmlaccountcontent += "</table>";

        htmlaccountcontent += '<div onclick="showlogout()">log out?</div>'
        htmlaccount.html(htmlaccountcontent);
    }
}

let login = function(txt) {
    // alert(txt);
    txt = txt.replace(/\r|\n/g, "");
    if (txt === "Log in Done!") {
        hideall();
        $("#loginsuccess").show();
    } else {
        hideall();
        $("#loginfail").show();
    }
}

let logout = function(txt) {
    // alert(txt);
    txt = txt.replace(/\r|\n/g, "");
    if (txt === "Log out Done!") {
        hideall();
        $("#logoutsuccess").show();
    } else {
        hideall();
        $("#logoutfail").show();
    }
}

let sale = function(txt) {
    let salelist = txt.split("_salelistsplit_");
    let htmlsalelist = $("#salelist");

    let htmlsalelistcontent = "";
    let cnt = 0;
    htmlsalelistcontent += '<table>';
    salelist.forEach(sale => {
        let saleinfo = sale.split("_salesplit_");

        let htmlsalecontent = "<tr>";
        saleinfo.forEach(info => {
            htmlsalecontent += "<td>" + info + "</td>";
        });
        htmlsalecontent +=
            '<td>' +
            '<input id="selected' + cnt + '" type="checkbox">' +
            '</td>';
        htmlsalecontent += "</tr>";

        htmlsalelistcontent += htmlsalecontent;
        cnt++;
    });
    htmlsalelistcontent += '</table>';
    htmlsalelistcontent += '<button id="saleconfirm">confirm</button>';
    htmlsalelist.html(htmlsalelistcontent);
}

let buy = function(txt) {
    txt = txt.replace(/\r|\n/g, "");
    if (txt === "Sale Done!") {
        hideall();
        $("#salesuccess").show();
    } else {
        hideall();
        $("#salefail").show();
    }
}

let getdata = function(request) {
    // alert(request);
    $.ajax({
        async: false,
        url: "/uirequest?request=" + request,
        type: "POST",
        data: {},
        contentType: "application/json;charset=UTF-8",
        accept: "*/*",
        success: function(txt) {
            // alert(txt);
            let sign = txt.split("_typesign_")[0];
            switch (sign) {
                case "product":
                    product(txt.split("_typesign_")[1]);
                    break;
                case "account":
                    account(txt.split("_typesign_")[1]);
                    break;
                case "login":
                    login(txt.split("_typesign_")[1]);
                    break;
                case "logout":
                    logout(txt.split("_typesign_")[1]);
                    break;
                case "getsale":
                    sale(txt.split("_typesign_")[1]);
                    break;
                case "buy":
                    buy(txt.split("_typesign_")[1]);
                    break;
                default:
                    break;
            }
        }
    });
}

let productgetdata = function() {
    hideall();
    getdata("product");
}

let accountgetdata = function() {
    hideall();
    getdata("account");
}

let buygetdata = function() {
    loginexam(
        function() {
            let cnt = 1;
            let productnumberlist = [];
            while ($("#productnumber" + cnt).html() != undefined) {
                let n = parseInt($("#productnumber" + cnt).html());
                productnumberlist.push(n);
                cnt++;
            }

            confirm(productnumberlist);
        },
        function() {
            showlogin();
        }
    );
}

let showlogin = function() {
    hideall();
    $("#login").show();
    $("#username").val() = "";
    $("#password").val() = "";
}

let logingetdata = function() {
    let username = $("#username").val();
    let password = $("#password").val();
    getdata("login" + "_split_" + username + "_split_" + password);
}

let showlogout = function() {
    hideall();
    $("#logout").show();
    getdata("logout");
}

let confirm = function(productnumberlist) {
    hideall();
    $("#salelist").show();
    getdata("getsale");
    $("#saleconfirm").click(function() {
        let cnt = 0;
        let salelist = [];
        while ($("#selected" + cnt).val() != undefined) {
            if ($("#selected" + cnt).is(":checked")) {
                salelist.push(cnt);
            }
            cnt++;
        }
        salelist = salelist.reduce(function(prev, i) {
            return prev + " " + i;
        });

        let p = 0;
        let n = 0;
        for (let i = 0; i < productnumberlist.length; i++) {
            if (productnumberlist[i] > 0) {
                n = productnumberlist[i];
                p = i + 1;
            }
        }
        getdata("buy_split_" + p + " " + n + "_split_" + salelist);
    });
}