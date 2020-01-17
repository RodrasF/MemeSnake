package workspace.android.memesnake.repository.domain

data class UserDto(val login : String,
                   val avatar_url : String,
                   val name : String?,
                   val email : String?,
                   val followers : Int)

data class MemesDto(val count : Int,
                   val memes : List<MemeDto>)

data class MemeDto(val url : String,
                   val postLink: String,
                   val subreddit: String,
                   val title: String)