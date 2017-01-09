<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>
<body>
<div class="modal-body">
    <div class="avatar-body">

        <!-- Crop and preview -->
        <div class="row">
            <div class="col-xs-9"> 
                <div class="img-container"><img id="image" src="${imgUrl }"></div>
            </div>
            <div class="col-xs-3"> 
                <div class="docs-preview clearfix">
                    <div class="img-preview preview-lg"></div>
                    <div class="img-preview preview-md"></div>
                    <div class="img-preview preview-sm"></div>
                </div>
            </div>
        </div>

        <div class="row avatar-btns text-center p-t20">
            <label class="btn btn-primary m-lr10" for="inputImage">
                <input type="file" class="sr-only" id="inputImage" name="file" accept="image/jpeg,image/png,image/gif"><span>上传图片</span>
            </label>
            <button id="button" class="btn btn-primary avatar-save m-lr10" type="submit">保存修改</button>
        </div>
		
        <div id="result"></div>
    </div>
</div>

</body>
<script src="/static/page/js/jquery-1.11.0.min.js"></script>
<script src="/static/page/js/bootstrap.min.js"></script>
<script src="/static/page/js/common.js"></script>
<script src="/static/js/plugins/layer/layer.js"></script>

<link href="/static/js/plugins/cropper/cropper.min.css" rel="stylesheet"/>
<script src="/static/js/plugins/cropper/cropper.min.js"></script>
<script src="/static/js/cropper_main_live.js"></script>
</html>