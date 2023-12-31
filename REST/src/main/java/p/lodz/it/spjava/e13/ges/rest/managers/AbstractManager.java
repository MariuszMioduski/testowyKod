package p.lodz.it.spjava.e13.ges.rest.managers;

import jakarta.annotation.Resource;
import jakarta.ejb.EJBException;
import jakarta.ejb.SessionContext;
import jakarta.ejb.SessionSynchronization;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class AbstractManager implements SessionSynchronization {


    public final static int TX_ROLLBACK_RETRY_LIMIT = 3;//limit prób ponawiania odwołanych transakcji aplikacyjnych

    protected static final Logger LOGGER = Logger.getGlobal();

    @Resource
    protected SessionContext sctx;

    private String transactionId;

    private boolean lastTransactionRollback;

    public boolean isLastTransactionRollback() {
        return lastTransactionRollback;
    }

    public void afterBegin() throws EJBException {
        transactionId = Long.toString(System.currentTimeMillis())
                + ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        LOGGER.log(Level.INFO, "Transakcja TXid={0} rozpoczęta w {1}, tożsamość: {2}",
                new Object[]{transactionId, this.getClass().getName(), sctx.getCallerPrincipal().getName()});
    }

    public void beforeCompletion() throws EJBException {
        LOGGER.log(Level.INFO, "Transakcja TXid={0} przed zatwierdzeniem w {1}, tożsamość {2}",
                new Object[]{transactionId, this.getClass().getName(), sctx.getCallerPrincipal().getName()});
    }

    public void afterCompletion(boolean committed) throws EJBException {
        lastTransactionRollback = !committed;
        LOGGER.log(Level.INFO, "Transakcja TXid={0} zakończona w {1} poprzez {3}, tożsamość {2}",
                new Object[]{transactionId, this.getClass().getName(), sctx.getCallerPrincipal().getName(),
                        committed ? "ZATWIERDZENIE" : "ODWOŁANIE"});
    }
}
