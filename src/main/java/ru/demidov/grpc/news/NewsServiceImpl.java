package ru.demidov.grpc.news;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;

public class NewsServiceImpl extends NewsServiceGrpc.NewsServiceImplBase {

	@Override
	public void get(Empty request, StreamObserver<NewsResponse> responseObserver) {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		var newsFirst = NewsGrpc.newBuilder()
				.setId(1)
				.setBody("Body 1")
				.setCaption("Caption 1")
				.setCategory("Category 1")
				.setPublicationDate(LocalDateTime.now().format(formatter))
				.build();
		var newsSecond = NewsGrpc.newBuilder()
				.setId(2)
				.setBody("Body 2")
				.setCaption("Caption 2")
				.setCategory("Category 2")
				.setPublicationDate(LocalDateTime.now().format(formatter))
				.build();
		var newsResponse = NewsResponse.newBuilder()
				.addItems(newsFirst)
				.addItems(newsSecond)
				.build();

		responseObserver.onNext(newsResponse);
		responseObserver.onCompleted();
	}
}
