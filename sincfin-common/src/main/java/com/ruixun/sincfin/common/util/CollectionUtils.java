package com.ruixun.sincfin.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


public class CollectionUtils {
	
	/** map默认初始大小 */
	public static final int MAP_DEFAULT_INITIAL_CAPACITY = 16;
	/** map默认增长因子，当Map的size达到 容量*增长因子时，开始扩充Map */
	public static final float MAP_DEFAULT_LOAD_FACTOR = 0.75f;
	
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}
	
	// ----------------------------------------------------------------------------------------------- new HashMap
		/**
		 * 新建一个HashMap
		 * 
		 * @param <K> Key类型
		 * @param <V> Value类型
		 * @return HashMap对象
		 * @see MapUtil#newHashMap()
		 */
		public static <K, V> HashMap<K, V> newHashMap() {
			return new HashMap<K, V>();
		}

		/**
		 * 新建一个HashMap
		 * 
		 * @param <K> Key类型
		 * @param <V> Value类型
		 * @param size 初始大小，由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75
		 * @param isOrder Map的Key是否有序，有序返回 {@link LinkedHashMap}，否则返回 {@link HashMap}
		 * @return HashMap对象
		 * @since 3.0.4
		 * @see MapUtil#newHashMap(int, boolean)
		 */
		public static <K, V> HashMap<K, V> newHashMap(int size, boolean isOrder) {
			int initialCapacity = (int) (size / MAP_DEFAULT_LOAD_FACTOR);
			return isOrder ? new LinkedHashMap<K, V>(initialCapacity) : new HashMap<K, V>(initialCapacity);
		}

		/**
		 * 新建一个HashMap
		 * 
		 * @param <K> Key类型
		 * @param <V> Value类型
		 * @param size 初始大小，由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75
		 * @return HashMap对象
		 * @see MapUtil#newHashMap(int)
		 */
		public static <K, V> HashMap<K, V> newHashMap(int size) {
			return newHashMap(size, false);
		}

		// ----------------------------------------------------------------------------------------------- new HashSet
		/**
		 * 新建一个HashSet
		 * 
		 * @param <T> 集合元素类型
		 * @param ts 元素数组
		 * @return HashSet对象
		 */
		@SafeVarargs
		public static <T> HashSet<T> newHashSet(T... ts) {
			return newHashSet(false, ts);
		}

		/**
		 * 新建一个HashSet
		 * 
		 * @param <T> 集合元素类型
		 * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回 {@link HashSet}
		 * @param ts 元素数组
		 * @return HashSet对象
		 */
		@SafeVarargs
		public static <T> HashSet<T> newHashSet(boolean isSorted, T... ts) {
			if (null == ts) {
				return isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
			}
			int initialCapacity = Math.max((int) (ts.length / .75f) + 1, 16);
			HashSet<T> set = isSorted ? new LinkedHashSet<T>(initialCapacity) : new HashSet<T>(initialCapacity);
			for (T t : ts) {
				set.add(t);
			}
			return set;
		}

		/**
		 * 新建一个HashSet
		 * 
		 * @param <T> 集合元素类型
		 * @param collection 集合
		 * @return HashSet对象
		 */
		public static <T> HashSet<T> newHashSet(Collection<T> collection) {
			return newHashSet(false, collection);
		}

		/**
		 * 新建一个HashSet
		 * 
		 * @param <T> 集合元素类型
		 * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回{@link HashSet}
		 * @param collection 集合，用于初始化Set
		 * @return HashSet对象
		 */
		public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
			return isSorted ? new LinkedHashSet<T>(collection) : new HashSet<T>(collection);
		}

		/**
		 * 新建一个HashSet
		 * 
		 * @param <T> 集合元素类型
		 * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回{@link HashSet}
		 * @param iter {@link Iterator}
		 * @return HashSet对象
		 * @since 3.0.8
		 */
		public static <T> HashSet<T> newHashSet(boolean isSorted, Iterator<T> iter) {
			if (null == iter) {
				return newHashSet(isSorted, (T[]) null);
			}
			final HashSet<T> set = isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
			while (iter.hasNext()) {
				set.add(iter.next());
			}
			return set;
		}

		/**
		 * 新建一个HashSet
		 * 
		 * @param <T> 集合元素类型
		 * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回{@link HashSet}
		 * @param enumration {@link Enumeration}
		 * @return HashSet对象
		 * @since 3.0.8
		 */
		public static <T> HashSet<T> newHashSet(boolean isSorted, Enumeration<T> enumration) {
			if (null == enumration) {
				return newHashSet(isSorted, (T[]) null);
			}
			final HashSet<T> set = isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
			while (enumration.hasMoreElements()) {
				set.add(enumration.nextElement());
			}
			return set;
		}

		// ----------------------------------------------------------------------------------------------- new ArrayList
		/**
		 * 新建一个ArrayList
		 * 
		 * @param <T> 集合元素类型
		 * @param values 数组
		 * @return ArrayList对象
		 */
		@SafeVarargs
		public static <T> ArrayList<T> newArrayList(T... values) {
			if (null == values) {
				return new ArrayList<>();
			}
			ArrayList<T> arrayList = new ArrayList<T>(values.length);
			for (T t : values) {
				arrayList.add(t);
			}
			return arrayList;
		}

		/**
		 * 新建一个ArrayList
		 * 
		 * @param <T> 集合元素类型
		 * @param collection 集合
		 * @return ArrayList对象
		 */
		public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
			if (null == collection) {
				return new ArrayList<>();
			}
			return new ArrayList<T>(collection);
		}

		/**
		 * 新建一个ArrayList<br>
		 * 提供的参数为null时返回空{@link ArrayList}
		 * 
		 * @param <T> 集合元素类型
		 * @param iterable {@link Iterable}
		 * @return ArrayList对象
		 * @since 3.1.0
		 */
		public static <T> ArrayList<T> newArrayList(Iterable<T> iterable) {
			return (null == iterable) ? new ArrayList<T>() : newArrayList(iterable.iterator());
		}

		/**
		 * 新建一个ArrayList<br>
		 * 提供的参数为null时返回空{@link ArrayList}
		 * 
		 * @param <T> 集合元素类型
		 * @param iter {@link Iterator}
		 * @return ArrayList对象
		 * @since 3.0.8
		 */
		public static <T> ArrayList<T> newArrayList(Iterator<T> iter) {
			final ArrayList<T> list = new ArrayList<>();
			if (null == iter) {
				return list;
			}
			while (iter.hasNext()) {
				list.add(iter.next());
			}
			return list;
		}

		/**
		 * 新建一个ArrayList<br>
		 * 提供的参数为null时返回空{@link ArrayList}
		 * 
		 * @param <T> 集合元素类型
		 * @param enumration {@link Enumeration}
		 * @return ArrayList对象
		 * @since 3.0.8
		 */
		public static <T> ArrayList<T> newArrayList(Enumeration<T> enumration) {
			final ArrayList<T> list = new ArrayList<>();
			if (null == enumration) {
				return list;
			}
			while (enumration.hasMoreElements()) {
				list.add(enumration.nextElement());
			}
			return list;
		}

		/**
		 * 新建一个CopyOnWriteArrayList
		 * 
		 * @param <T> 集合元素类型
		 * @param collection 集合
		 * @return {@link CopyOnWriteArrayList}
		 */
		public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList(Collection<T> collection) {
			return (null == collection) ? (new CopyOnWriteArrayList<T>()) : (new CopyOnWriteArrayList<T>(collection));
		}

		/**
		 * 去重集合
		 * 
		 * @param <T> 集合元素类型
		 * @param collection 集合
		 * @return {@link ArrayList}
		 */
		public static <T> ArrayList<T> distinct(Collection<T> collection) {
			if (isEmpty(collection)) {
				return new ArrayList<>();
			} else if (collection instanceof Set) {
				return new ArrayList<>(collection);
			} else {
				return new ArrayList<>(new LinkedHashSet<>(collection));
			}
		}
	
	
	/**
	 * {@link Iterable}转为{@link Collection}<br>
	 * 首先尝试强转，强转失败则构建一个新的{@link ArrayList}
	 * 
	 * @param <E> 集合元素类型
	 * @param iterable {@link Iterable}
	 * @return {@link Collection} 或者 {@link ArrayList}
	 * @since 3.0.9
	 */
	public static <E> Collection<E> toCollection(Iterable<E> iterable) {
		return (iterable instanceof Collection) ? (Collection<E>) iterable : newArrayList(iterable.iterator());
	}

	/**
	 * 以 conjunction 为分隔符将集合转换为字符串
	 * 
	 * @param <T> 集合元素类型
	 * @param iterable {@link Iterable}
	 * @param conjunction 分隔符
	 * @return 连接后的字符串
	 */
	public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
		if (null == iterable) {
			return null;
		}
		return join(iterable.iterator(), conjunction);
	}

	/**
	 * 以 conjunction 为分隔符将集合转换为字符串<br>
	 * 如果集合元素为数组、{@link Iterable}或{@link Iterator}，则递归组合其为字符串
	 * 
	 * @param <T> 集合元素类型
	 * @param iterator 集合
	 * @param conjunction 分隔符
	 * @return 连接后的字符串
	 */
	public static <T> String join(Iterator<T> iterator, CharSequence conjunction) {
		if (null == iterator) {
			return null;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		T item;
		while (iterator.hasNext()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(conjunction);
			}

			item = iterator.next();
			if (ArrayUtils.isArray(item)) {
				sb.append(ArrayUtils.join(ArrayUtils.wrap(item), conjunction));
			} else if (item instanceof Iterable<?>) {
				sb.append(join((Iterable<?>) item, conjunction));
			} else if (item instanceof Iterator<?>) {
				sb.append(join((Iterator<?>) item, conjunction));
			} else {
				sb.append(item);
			}
		}
		return sb.toString();
	}


}
