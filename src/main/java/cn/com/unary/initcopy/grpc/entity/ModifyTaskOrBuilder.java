// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: init_copy_entity.proto

package cn.com.unary.initcopy.grpc.entity;

public interface ModifyTaskOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.com.unary.initcopy.grpc.entity.ModifyTask)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *对应的任务id
   * </pre>
   *
   * <code>optional string taskId = 1;</code>
   */
  java.lang.String getTaskId();
  /**
   * <pre>
   *对应的任务id
   * </pre>
   *
   * <code>optional string taskId = 1;</code>
   */
  com.google.protobuf.ByteString
      getTaskIdBytes();

  /**
   * <pre>
   *限速 以M为单位  0为不限速
   * </pre>
   *
   * <code>optional int32 speedLimit = 2;</code>
   */
  int getSpeedLimit();
}
