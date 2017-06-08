package net.brian.coding.db;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item29: Consider typesafe heterogeneous containers
 * 
 * 类型安全的异构容器：它的所有键都有不同的参数化类型，可以是Class<String>，可以是Class<Integer>
 * 当你向它请求String的时候，它绝不会返回一个Integer给你
 * 
 * 集合API说明了泛型的一般用法，限制你每个容器只能有固定数目的类型参数
 * 你可以通过将类型参数放在键上而不是容器上来避开这一限制
 * 对于这种类型安全的异构容器可以用Class对象作为键，以这种方式使用的Class对象称作类型令牌
 * 
 * 你可以使用定制的键类型，如：用一个DatabaseRow类型表示一个数据库行，用泛型Column<T>作为它的键
 * 
 */
public class DatabaseRowByGeneric {
//TODO: 泛型：用一个DatabaseRow类型表示一个数据库行，用泛型Column<T>作为它的键
}
