$("#savepost").click(() => {
  let postId = $("#post-id").val();
  let postTitle = $("#post-title").val();
  let postContent = $("#post-content").val();

  console.log(postId + " " + postTitle + " " + postContent);

  $.ajax({
    url: "http://localhost:8080/blog/savePost",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify({
      id: postId,
      title: postTitle,
      content: postContent,
    }),

    success: function (result) {
      console.log(result);
      alert("Saved");
    },
    error: function (error) {
      console.log(error);
      alert("Not saved");
    },
  });
});

$("#updatePost").click(() => {
  let postId = $("#post-id").val();
  let postTitle = $("#post-title").val();
  let postContent = $("#post-content").val();

  console.log("Updating post:", postId, postTitle, postContent);

  $.ajax({
    url: "http://localhost:8080/blog/updatePost",
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify({
      id: postId,
      title: postTitle,
      content: postContent,
    }),

    success: function (result) {
      console.log(result);
      alert("Post updated successfully");
    },
    error: function (error) {
      console.log(error);
      alert("Failed to update post");
    },
  });
});

$("#deletePost").click(() => {
  let postId = $("#post-id").val();

  console.log("Deleting post with ID:", postId);

  $.ajax({
    url: "http://localhost:8080/blog/deletePost/" + postId,
    method: "DELETE",
    success: function (result) {
      console.log(result);
      alert("Post deleted successfully");
    },
    error: function (error) {
      console.log(error);
      alert("Failed to delete post");
    },
  });
});

function getAllPosts() {
  $.ajax({
    url: "http://localhost:8080/blog/getAllpost",
    method: "GET",
    dataType: "json",
    success: function (data) {
      var tableBody = $("#data-table-body");
      tableBody.empty(); 

      data.forEach(function (item) {
        var row =
          "<tr>" +
          "<td>" + item.id + "</td>" +
          "<td>" + (item.title ? item.title : "null") + "</td>" +
          "<td>" + (item.content ? item.content : "null") + "</td>" +
          '<td>' +
          '<button class="btn btn-warning update-post" data-id="' + item.id + '" data-title="' + item.title + '" data-content="' + item.content + '">Update</button> ' +
          '<button class="btn btn-danger delete-post" data-id="' + item.id + '">Delete</button>' +
          '</td>' +
          "</tr>";
        tableBody.append(row);
      });

   
      $(".delete-post").click(()=> {
        var postId = $(this).data("id");
        deletePost(postId);
      });

      
      $(".update-post").click(function () {
        var postId = $(this).data("id");
        var postTitle = $(this).data("title");
        var postContent = $(this).data("content");

      
        $("#update-post-id").val(postId);
        $("#update-post-title").val(postTitle);
        $("#update-post-content").val(postContent);

      
        $("#updateModal").modal("show");
      });
    },
    error: function (error) {
      console.error("Error fetching data:", error);
      alert("An error occurred while fetching data.");
    },
  });
}


function deletePost(postId) {
  $.ajax({
    url: "http://localhost:8080/blog/deletePost/" + postId,
    method: "DELETE",
    success: function (result) {
      console.log(result);
      alert("Post deleted successfully");
      getAllPosts(); 
    },
    error: function (error) {
      console.log(error);
      alert("Failed to delete post");
    },
  });
}


function updatePost() {
  let postId = $("#update-post-id").val();
  let postTitle = $("#update-post-title").val();
  let postContent = $("#update-post-content").val();

  console.log("Updating post:", postId, postTitle, postContent);

  $.ajax({
    url: "http://localhost:8080/blog/updatePost",
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify({
      id: postId,
      title: postTitle,
      content: postContent,
    }),

    success: function (result) {
      console.log(result);
      alert("Post updated successfully");
      $("#updateModal").modal("hide");
      getAllPosts(); 
    },
    error: function (error) {
      console.log(error);
      alert("Failed to update post");
    },
  });
}

// Initial call to populate the table
$(document).ready(function () {
  getAllPosts();

  // Attach click event to save changes button in modal
  $("#save-updates").click(function () {
    updatePost();
  });
});



$("#getAllPost").click(function () {
  $.ajax({
    url: "http://localhost:8080/blog/getAllpost",
    method: "GET",
    dataType: "json",
    success: function (data) {
      var tableBody = $("#data-table-body");
      tableBody.empty();
      data.forEach(function (item) {
        var row =
          "<tr>" +
          "<td>" +
          item.id +
          "</td>" +
          "<td>" +
          (item.title ? item.title : "null") +
          "</td>" +
          "<td>" +
          (item.content ? item.content : "null") +
          "</td>" +
          "</tr>";
        tableBody.append(row);
      });
    },
    error: function (error) {
      console.error("Error fetching data:", error);
      alert("An error occurred while fetching data.");
    },
  });
});
