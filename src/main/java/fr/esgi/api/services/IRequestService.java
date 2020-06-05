package fr.esgi.api.services;

import fr.esgi.api.models.request.Request;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface IRequestService {
    /**
     * Send a Request via request synchronously.
     * @param request A Request to send.
     * @return A Boolean whose value is TRUE if sent successfully; otherwise
     *         FALSE.
     */
    Boolean send(Request request);

    /**
     * Send a Request via request asynchronously.
     * @param request A Request to send.
     */
    void sendAsync(Request request);

    /**
     * Send a Request via request asynchronously. Returns a Future&lt;Boolean&gt;
     * response allowing the client to obtain the status of the operation once
     * it is completed.
     * @param request A Request to send.
     * @return A Future&lt;Boolean&gt; whose value is TRUE if sent successfully;
     *         otherwise, FALSE.
     */
    Future<Boolean> sendAsyncWithResult(Request request);


    List<Request> findAll();

    Optional<Request> findById(Long id);

    Request create(Request request);

    Request update(Request request);

    void delete(Long id);

}
