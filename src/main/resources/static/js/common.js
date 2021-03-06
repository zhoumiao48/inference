// 依赖于jquery

/**
 * 通用Get方法
 * @param url 后端接口地址
 * @param data 传送的数据
 * @param successCallback 成功回调函数（可选）
 * @param errorCallBack 失败回调函数（可选）
 */
function ajaxGet(url, data, successCallback, errorCallback) {
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        cache: false,
        success: successCallback,
        error: errorCallback
    })
}

/**
 * 通用POST方法
 * @param url 后端接口地址
 * @param data 传送的数据
 * @param successCallback 成功回调函数（可选）
 * @param errorCallback 失败回调函数（可选）
 */
function ajaxPost(url, data, successCallback, errorCallback, async = true) {
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        cache: false,
        success: successCallback,
        error: errorCallback,
        async: async
    })
}

/**
 * 通用POST方法，data会被自动转化为json格式的字符串
 * @param url 后端接口地址
 * @param data 传送的数据
 * @param successCallback 成功回调函数（可选）
 * @param errorCallback 失败回调函数（可选）
 */
function ajaxPostJSON(url, data, successCallback, errorCallback, async = true) {
    $.ajax({
        type: 'POST',
        url: url,
        data: JSON.stringify(data),
        cache: false,
        success: successCallback,
        error: errorCallback,
        dataType: 'json',
        contentType: 'application/json;charset=utf-8',
        async: async
    })
}

/**
 * @deprecated 使用下面的方法替换
 * 格式化时间戳，返回格式 YYYY-MM-DD HH:mm:ss
 * @param timestamp
 */
function formatTimestamp(timestamp) {
    if (timestamp == null || timestamp === '' || timestamp === 0) return '';
    let date = new Date(timestamp); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' : '') + date.getDate() + ' ';
    let h = (date.getHours() < 10 ? '0' : '') + date.getHours() + ':';
    let m = (date.getMinutes() < 10 ? '0' : '') + date.getMinutes() + ':';
    let s = (date.getSeconds() < 10 ? '0' : '') + date.getSeconds();
    return Y + M + D + h + m + s;
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * 例子：
 * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

Array.prototype.contains = function (obj) {
    let i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
};

function copy(object) {
    return JSON.parse(JSON.stringify(object));
}

// 文件上传状态
const UploadStatus = {
    QUEUED: 0, // 队列中（尚未开始）
    UPLOADING: 1, // 上传中
    DONE: 2, // 完成
    FAILED: 3, // 失败
    PAUSE: 4 // 暂停
};

// 后端返回状态
const MsgType = {
    SUCCESS: 0,
    ERROR: 1,
    WARNING: 2,
    INFO: 3
};

// 高度调整
function adjustHeight() {
    let height = $(window).height() + "px";
    document.getElementById("layoutContainer").style.height = height;
}
// 时间戳格式化
function transformTime(timestamp = +new Date()) {
    if (timestamp) {
        var time = new Date(timestamp);
        var y = time.getFullYear();
        var M = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var m = time.getMinutes();
        var s = time.getSeconds();
        return y + '-' + addZero(M) + '-' + addZero(d) + ' ' + addZero(h) + ':' + addZero(m) + ':' + addZero(s);
    } else {
        return '';
    }
}

function addZero(m) {
    return m < 10 ? '0' + m : m;
}

// 根据角色返回侧边栏数组
function showAsideByRole(plusUser) {
    if (plusUser.role == '管理员') {
        return;
    }
    app.visibility.userManagement = false;
    app.visibility.dictManagement = false;
    if (plusUser.role == '普通用户') {
        app.visibility.ruleManagement = false;
    }
}

// 登出
function logout() {
    ajaxGet("/user/logout", null, function (d) {
        if (d.code == 'success') {
            app.$message({
                message: d.message,
                type: 'success'
            });
            app.loading = true;
            setTimeout(function () {
                window.location.href = '/view/goIndex';
            }, 500);
        }
    })
}

// 打开登出框
function openLogout() {
    app.$confirm('确认登出系统?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(
        function () {
            logout();
        }).catch(() => {
        app.$message({
            type: 'info',
            message: '已取消登出'
        });
    });
}

// 跳转到个人中心页面
function goUserCenter() {
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goUserCenter';
    }, 150);
}

// 跳转到事实推理页面
function goInference() {
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goInference';
    }, 150);
}

// 跳转到规则录入页面
function goAddRule() {
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goAddRule';
    }, 150);
}

// 跳转到添加用户页面
function goAddUser(){
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goAddUser';
    }, 150);
}

// 跳转到规则集合页面
function goRuleList(){
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goRuleList';
    }, 150);
}

// 跳转到事实知识集合页面
function goFactList() {
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goFactList';
    }, 150);
}

// 跳转到用户集合页面
function goUserList() {
    app.loading = true;
    setTimeout(function () {
        window.location.href = '/view/goUserList';
    }, 150);
}

/**
 * generateUUID 生成UUID
 * @returns {string} 返回字符串
 */
function generateUUID(){
    let d = new Date().getTime();
    let uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x7|0x8)).toString(16);
    });
    return uuid;
}