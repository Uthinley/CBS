<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="..." alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="..." alt="Second slide">
        </div>
        <div class="carousel-item">width
            <img class="d-block w-100" src="..." alt="Third slide">
        </div>
    </div>
</div>

<div class="carousel-item">
    <div class="row text-center">
        <div class="col-md-4 wrapper">
            <div class="text-center">
                <img class="img-responsive" style="margin-left:0px;overflow: hidden;" src="resources/images/QR.png">
            </div>
        </div>
        </div>
        <div class="row text-center">
            <div class="col-md-4 wrapper">
                <div class="text-center">
                    <img class="img-responsive" style="margin-left:0px;width: 20%;height: 20%;overflow: hidden;" src="resources/images/QR.png">
                </div>
            </div>
        </div>
    </div>
</div>

<c:forEach var="item" items="${wwImage}">
    <img class="img-responsive" style="width: 100%;height: 60%;overflow: hidden;" src="<c:url value='${item.filePath}'/>">
</c:forEach>