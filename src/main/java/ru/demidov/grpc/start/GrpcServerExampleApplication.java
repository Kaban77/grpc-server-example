package ru.demidov.grpc.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.ServerBuilder;
import ru.demidov.grpc.news.NewsServiceImpl;

public class GrpcServerExampleApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServerExampleApplication.class);

	public static void main(String[] args) {
		try {
			var server = ServerBuilder
					.forPort(8084)
					.addService(new NewsServiceImpl())
					.build();
			server.start();
			server.awaitTermination();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> server.shutdown()));
		} catch (Exception e) {
			LOGGER.error("Error!", e);
		}
	}
}
