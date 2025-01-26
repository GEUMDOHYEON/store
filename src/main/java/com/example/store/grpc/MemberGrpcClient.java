package com.example.store.grpc;

import com.example.grpc.Member;
import com.example.grpc.MemberRequest;
import com.example.grpc.MemberResponse;
import com.example.grpc.MemberServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class MemberGrpcClient {
    @GrpcClient("member-grpc-server")
    private MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub;

    public MemberResponse getMember(String name) {
        MemberRequest request = MemberRequest.newBuilder().setName(name).build();
        return memberServiceBlockingStub.getMember(request);
    }
}
