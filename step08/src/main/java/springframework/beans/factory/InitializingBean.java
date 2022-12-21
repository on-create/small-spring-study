package springframework.beans.factory;

/**
 * Interface to be implemented by beans that need to react once all their
 * properties have been set by a BeanFactory: for example, to perform custom
 * initialization, or merely to check that all mandatory properties have been set.
 * <br/>
 * ʵ�ִ˽ӿڵ� Bean ���󣬻��� BeanFactory �������Ժ�������Ӧ�Ĵ����磺ִ���Զ����ʼ�������߽�������Ƿ�����������ǿ�����ԡ�
 */
public interface InitializingBean {

    /**
     * Bean �����������������
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
