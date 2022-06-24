var Dom = {
    ulDriverList: 'ul.driver-list',
    checkBoxAgree: '#checkboxAgree',
    btnAddDriver: '#btnAddDriver',
    divAddDriver: 'div.renter-add'
};

function refreshPage() {
    /// <summary>
    /// 强制刷新当前页
    /// </summary>
    /// <returns type=""></returns>
    location.reload(true);
}

function DriverModel(id, name, phone, cardType, cardNo, year, month) {
    /// <summary>
    /// 租车人数据模型
    /// </summary>
    /// <param name="id"></param>
    /// <param name="name"></param>
    /// <param name="phone"></param>
    /// <param name="cardType"></param>
    /// <param name="cardNo"></param>
    /// <param name="year"></param>
    /// <param name="month"></param>
    /// <returns type=""></returns>
    var self = this;
    self.Id = id||0;
    self.Name = name;
    self.CellPhone = phone;
    self.CardType = cardType;
    self.CardNo = cardNo;
    self.Year = year;
    self.Month = month;
    self.IsNew = !id;
}

function getDriverModelFromLi($li) {
    /// <summary>
    /// 从li元素中读取租车人数据模型
    /// </summary>
    /// <param name="$li"></param>
    var driverId = $li.data('driverId');
    var driverName = $li.data('name');
    var driverPhone = $li.data('cellphone');
    var driverIdType = $li.data('idcardtype');
    var driverIdNo = $li.data('idcardNumber');
    var driverYear = $li.data('year');
    var driverMonth = $li.data('month');
    var driver = new DriverModel(driverId, driverName, driverPhone, driverIdType, driverIdNo, driverYear, driverMonth);
    return driver;
}

function validateForm($form) {
    /// <summary>
    /// 验证表单
    /// </summary>
    /// <param name="$form"></param>
    /// <returns type=""></returns>
    var name = $form.find("input[name='Name']").val();
    if (name == null || name == "") {
        alert("请输入姓名");
        return false;
    }
    var cellPhone = $form.find("input[name='CellPhone']").val();
    if (cellPhone == null || !/^1\d{10}$/.test(cellPhone)) {
        alert("请输入手机号");
        return false;
    }
    //var idCardType = $form.find("select[name='IdCardType']").val();
    var idCardNo = $form.find("input[name='IdCardNo']").val();
    if (idCardNo==null ||idCardNo=="") {
        alert("请输入证件号码");
        return false;
    }
    //var driverYear = $form.find("select[name='DriverYear']").val();
    //var driverMonth = $form.find("select[name='DriverMonth']").val();

    return true;
}

$(function () {

    $(Dom.ulDriverList).on('click', '.default', function () {
        /// <summary>
        /// 设置默认租车人
        /// </summary>
        var $li = $(this).parent().parent();
        var driverId = $li.data('driverId');
        var postData= {
            Id: driverId
        }
        $.post(URL.setDefault, postData, function(data) {
            alert(data.Message);
            $li.addClass('ck').siblings().removeClass('ck');
        });
    }).on('click', '.delete', function () {
        /// <summary>
        /// 删除租车人
        /// </summary>
        if (!confirm("确认删除吗?")) {
            return false;
        }
        var $li = $(this).parent().parent();
        var driverId = $li.data('driverId');
        var deleteData = {
            Id: driverId
        }
        $.post(URL.deleteDriver, deleteData,function(data) {
            alert(data.Message);
            if (data.Success) {
                $li.remove();
                refreshPage();
            };
        });
    }).on('click', '.modify', function () {
        /// <summary>
        /// 点击修改租车人按钮
        /// </summary>
        var $li = $(this).parent().parent();
        ///编辑面板
        var $divEditor = $li.children('div.invoice-modify');
        if ($divEditor.is(':hidden')) {
            var driver = getDriverModelFromLi($li);
            ko.cleanNode($divEditor[0]);
            ko.applyBindings(driver, $divEditor[0]);
            $divEditor.show();
        } else {
            ///隐藏编辑面板
            $divEditor.hide();
        }
    }).on('click', 'a.btn-save', function () {
        /// <summary>
        /// 保存修改联系人
        /// </summary>
        /// <returns type=""></returns>
        var $form = $(this).parent().siblings('form');

        if (!validateForm($form)) {
            return false;
        }

        var formData = $form.serialize();
        //alert(formData);
        $.post(URL.saveDriver, formData, function (data) {
            alert(data.Message);
            refreshPage();
        });
    });


    ///关闭修改面板
    $('div.renter').on('click', '.btn-close', function () {     
        $(this).parent('div.edit-panel').hide();
    });

    ///打开添加租车人
    $('#btnAddDriver').on('click', function () {
        var $divNewEditor = $(Dom.divAddDriver);
        if ($divNewEditor.find('div').length == 0) {
            var newDriver = new DriverModel();
            ko.cleanNode($divNewEditor[0]);
            ko.applyBindings(newDriver, $divNewEditor[0]);
        }
        $divNewEditor.show();
    });

    ///保存新租车人
    $('div.renter').on('click', 'a.add-new-driver', function () {
        var $form = $('.renter-add form');

        if (!validateForm($form)) {
            return false;
        }
        var formData = $form.serialize();
        $.post(URL.addDriver, formData, function (data) {
            if (!data.Success) {
                alert(data.Message);
                return false;
            }
            alert("添加成功！");
            refreshPage();
        });

    });

    /// 选中checkbox
    $(Dom.checkBoxAgree).change(function () {
        if ($(this).is(":checked")) {
            $(Dom.btnAddDriver).show();
        } else {
            $(Dom.btnAddDriver).hide();
            $(Dom.divAddDriver).hide();
        }
    });

    ///同意协议并关闭
    $('.btn-agree').click(function () {
        $(Dom.checkBoxAgree).prop("checked", 'checked');
        $('#agreementModal').modal('hide');
        $(Dom.checkBoxAgree).trigger('change');
    });
});