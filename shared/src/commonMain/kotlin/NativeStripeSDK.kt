import model.InitialiseParams

interface StripeSdk {
    suspend fun initialise()
//    suspend fun initialise(params: InitialiseParams?)
    

}
expect class provideStripeSdk(): StripeSdk