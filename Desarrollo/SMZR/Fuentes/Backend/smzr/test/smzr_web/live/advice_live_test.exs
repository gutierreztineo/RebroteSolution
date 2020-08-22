defmodule SmzrWeb.AdviceLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{description: "some description"}
  @update_attrs %{description: "some updated description"}
  @invalid_attrs %{description: nil}

  defp fixture(:advice) do
    {:ok, advice} = Monitoring.create_advice(@create_attrs)
    advice
  end

  defp create_advice(_) do
    advice = fixture(:advice)
    %{advice: advice}
  end

  describe "Index" do
    setup [:create_advice]

    test "lists all advices", %{conn: conn, advice: advice} do
      {:ok, _index_live, html} = live(conn, Routes.advice_index_path(conn, :index))

      assert html =~ "Listing Advices"
      assert html =~ advice.description
    end

    test "saves new advice", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.advice_index_path(conn, :index))

      assert index_live |> element("a", "New Advice") |> render_click() =~
               "New Advice"

      assert_patch(index_live, Routes.advice_index_path(conn, :new))

      assert index_live
             |> form("#advice-form", advice: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#advice-form", advice: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.advice_index_path(conn, :index))

      assert html =~ "Advice created successfully"
      assert html =~ "some description"
    end

    test "updates advice in listing", %{conn: conn, advice: advice} do
      {:ok, index_live, _html} = live(conn, Routes.advice_index_path(conn, :index))

      assert index_live |> element("#advice-#{advice.id} a", "Edit") |> render_click() =~
               "Edit Advice"

      assert_patch(index_live, Routes.advice_index_path(conn, :edit, advice))

      assert index_live
             |> form("#advice-form", advice: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#advice-form", advice: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.advice_index_path(conn, :index))

      assert html =~ "Advice updated successfully"
      assert html =~ "some updated description"
    end

    test "deletes advice in listing", %{conn: conn, advice: advice} do
      {:ok, index_live, _html} = live(conn, Routes.advice_index_path(conn, :index))

      assert index_live |> element("#advice-#{advice.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#advice-#{advice.id}")
    end
  end

  describe "Show" do
    setup [:create_advice]

    test "displays advice", %{conn: conn, advice: advice} do
      {:ok, _show_live, html} = live(conn, Routes.advice_show_path(conn, :show, advice))

      assert html =~ "Show Advice"
      assert html =~ advice.description
    end

    test "updates advice within modal", %{conn: conn, advice: advice} do
      {:ok, show_live, _html} = live(conn, Routes.advice_show_path(conn, :show, advice))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Advice"

      assert_patch(show_live, Routes.advice_show_path(conn, :edit, advice))

      assert show_live
             |> form("#advice-form", advice: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#advice-form", advice: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.advice_show_path(conn, :show, advice))

      assert html =~ "Advice updated successfully"
      assert html =~ "some updated description"
    end
  end
end
