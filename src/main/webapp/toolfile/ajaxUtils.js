!(function (global) {
        'use strict'
        var COMMON_ERROR = '服务器内部错误';
        var AJAX_SUCCESS_CODE = 10000;
        /**
         * ajax 默认方法
         * @param url
         * @param data
         * @param options
         * @returns {Promise}
         */
        var ajaxPromise = function (url, data, options, type, contentType) {
            options = options || {};
            return new Promise(function (resolve, reject) {
                $.ajax($.extend(options, {
                    type: type,
                    cache: !!options.hasOwnProperty('cache') && true === options['cache'] ? true : false,//默认不需要缓存
                    url: url,
                    contentType: !!contentType ? contentType : 'application/x-www-form-urlencoded',
                    data: data,
                    error: function () {
                        reject(COMMON_ERROR);
                    },
                    success: function (result) {
                        if (!!result.code && AJAX_SUCCESS_CODE == result.code) {
                            resolve(!!result.data ? result.data : []);
                        } else {
                            reject(!!result.msg ? result.msg : COMMON_ERROR);
                        }


                    }
                }));
            });
        };


        var ajaxUtils = function () {
        };

        /**
         * ajax GET请求
         * @param url     地址
         * @param data   参数
         * @param option  ajax 增强配置
         */
        ajaxUtils.get = function (url, data, options) {
            return ajaxPromise(url, data, options, 'GET');
        };
        /**
         * ajax POST请求
         * @param url     地址
         * @param data   参数
         * @param option  ajax增强配置
         */
        ajaxUtils.post = function (url, data, options) {
            return ajaxPromise(url, data, options, 'POST');
        };
        /**
         * ajax POST请求后台以requestBody接收
         * @param url     地址
         * @param data   参数
         * @param option  ajax增强配置
         */
        ajaxUtils.postBody = function (url, data, options) {
            return ajaxPromise(url, JSON.stringify(data), options, 'POST', 'application/json');
        };

        /**
         * 校验数据格式
         * @param val
         */
        ajaxUtils.getValidValue = function (val) {
            if (typeof val === 'number' && !isNaN(val)) {
                val = val.toString();
            }
            return !!val ? val : '-';
        }

        global.ajaxUtils = ajaxUtils;
    }
)(this);