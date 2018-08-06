package com.ruixun.sincfin.biz.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
 
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
 
/**
 * Created by guozp on 2017/8/29.
 */

//@Configuration
public class TxConfig {

    private static final int TX_METHOD_TIMEOUT = 5;

    private static final String AOP_POINTCUT_EXPRESSION =
            "execution (* com.ruixun.sincfin.biz.module.*.service.*.*(..))";

    /*事务拦截类型*/
    @Bean("txSource")
    public TransactionAttributeSource transactionAttributeSource(){

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
         /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );

        RuleBasedTransactionAttribute requiredTx =
                new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
                        Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("recharge", requiredTx);
        txMap.put("withdraw", requiredTx);
        txMap.put("investment", requiredTx);
        source.setNameMap( txMap );
 
        return source;
    }
 
    /**切面拦截规则 参数会自动从容器中注入*/
    @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor txInterceptor){
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(txInterceptor);
        pointcutAdvisor.setExpression(AOP_POINTCUT_EXPRESSION);
        return pointcutAdvisor;
    }
 
    /*事务拦截器*/
    @Bean("txInterceptor")
    TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager tx){
        return new TransactionInterceptor(tx , transactionAttributeSource()) ;
    }
}