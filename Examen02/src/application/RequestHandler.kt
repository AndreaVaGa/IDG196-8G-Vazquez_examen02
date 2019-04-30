package mx.edu.cetys.garay.andrea.application

interface RequestHandler<in TRequest : Request, out TResponse> {
    fun handle(message: TRequest): TResponse
}